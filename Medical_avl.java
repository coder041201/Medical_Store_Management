package medical_Store_Management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Medicine {

	Scanner scan = new Scanner(System.in);

	String name;
	String supplier_name;
	int price;
	int stock;
	ArrayList<String> diseases;
	int location;
	Date expiry_date;

	Medicine leftChild;
	Medicine rightChild;
	int height;

	Medicine(String name, String supplier_name, int price, int stock, int location, Date expiry_date) {

		this.name = name;
		this.supplier_name = supplier_name;
		this.price = price;
		this.stock = stock;
		this.location = location;
		this.expiry_date = expiry_date;
		diseases = new ArrayList<String>();

		leftChild = rightChild = null;
		height = 1;
	}

	void accept_diseases() {

		char ans;
		do {

			System.out.println("Enter name of disease which can be cured by this medicines");

			diseases.add(scan.next());

			System.out.println("Do you want to enter more diseases");
			ans = scan.next().charAt(0);

		} while (ans == 'y' || ans == 'Y');

		Collections.sort(diseases);
	}

	void display() {
		System.out.println("Name: " + name);
		System.out.println("Supplier name: " + supplier_name);
		System.out.println("Price: " + price);
		System.out.println("Stock: " + stock);
		System.out.println("Location: " + location);
		System.out.printf("%1$s %2$tB %2$td, %2$tY  ", "Expiry date:", expiry_date);
		System.out.println();
		System.out.println("Diseases that can be cured: ");
		System.out.println(diseases);
	}
}

public class Medical_avl {

	static int count;

	static final int MinimumStock = 10;

	Scanner scan = new Scanner(System.in);

	Medicine root;

	Medical_avl() {
		root = null;
	}

	int height(Medicine Medicine) {

		// Return 0 if Medicine is null
		if (Medicine == null) {
			return 0;
		} else {

			// recursively calling height on left and right subtree
			return Medicine.height;
		}
	}
	// Time complexity O(h)...h is height of tree

	int balance_factor(Medicine Medicine) {

		// Return 0 if Medicine is null
		if (Medicine == null) {
			return 0;
		}

		return height(Medicine.leftChild) - height(Medicine.rightChild);
	}
	// Time complexity O(h)...h is height of tree

	Medicine rightRotate(Medicine parent) {
		Medicine new_parent = parent.leftChild;
		Medicine new_leftChild = new_parent.rightChild;

		// Perform rotation
		new_parent.rightChild = parent;
		parent.leftChild = new_leftChild;

		// Update heights
		parent.height = Math.max(height(parent.leftChild), height(parent.rightChild)) + 1;
		new_parent.height = Math.max(height(new_parent.leftChild), height(new_parent.rightChild)) + 1;

		// Return new parent
		return new_parent;
	}
	// Time complexity O(1)

	Medicine leftRotate(Medicine parent) {
		Medicine new_parent = parent.rightChild;
		Medicine new_leftChild = new_parent.leftChild;

		// Perform rotation
		new_parent.leftChild = parent;
		parent.rightChild = new_leftChild;

		// Update heights
		parent.height = Math.max(height(parent.leftChild), height(parent.rightChild)) + 1;
		new_parent.height = Math.max(height(new_parent.leftChild), height(new_parent.rightChild)) + 1;

		// Return new root
		return new_parent;
	}
	// Time complexity O(1)

	Medicine insert(Medicine node, Medicine new_node) {

		// Perform the normal BST insertion
		if (node == null)
			return new_node;

		// inserting to left
		if (new_node.name.compareTo(node.name) < 0)
			// recursively calling insert
			node.leftChild = insert(node.leftChild, new_node);

		// inserting to right
		else if (new_node.name.compareTo(node.name) > 0)
			// recursively calling insert
			node.rightChild = insert(node.rightChild, new_node);

		// Duplicate nodes not allowed
		else {
			count--;
			System.out.println("This medicine is already present");
			return node;
		}

		// Update height of this ancestor Medicine
		node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));

		// Get the balance factor of this ancestor Medicine to check whether this
		// Medicine
		// became unbalanced
		int balance = balance_factor(node);

		// If this Medicine becomes unbalanced, then there are 4 cases Left Left Case
		if (balance > 1 && new_node.name.compareTo(node.leftChild.name) < 0) {

			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && new_node.name.compareTo(node.rightChild.name) > 0) {

			return leftRotate(node);
		}

		// Left Right Case
		if (balance > 1 && new_node.name.compareTo(node.leftChild.name) > 0) {

			node.leftChild = leftRotate(node.leftChild);

			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && new_node.name.compareTo(node.rightChild.name) < 0) {

			node.rightChild = rightRotate(node.rightChild);

			return leftRotate(node);
		}

		// return the Medicine
		return node;
	}
	// Time complexity O(h)...h is height of tree

	// Accept general information form customer
	void create() {

		String name;
		String supplier_name;
		int price;
		int stock;
		int location;
		Date expiry_date;

		// Name
		System.out.println("Enter the name:");
		name = scan.next();

		// Supplier name
		System.out.println("Enter the supplier");
		supplier_name = scan.next();

		// Price
		System.out.println("Enter the price per unit");
		price = scan.nextInt();

		// Stock
		System.out.println("Enter the stock");
		stock = scan.nextInt();

		// location
		System.out.println("Enter the location");
		location = scan.nextInt();

		// expiry_date
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		boolean flag = false;

		expiry_date = null;

		System.out.print("Enter expiry date (dd/mm/yyyy): ");
		while (!flag) {

			String cinput = scan.nextLine();
			if (null != cinput && cinput.trim().length() > 0) {
				try {
					expiry_date = format.parse(cinput);
					flag = true;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Enter valid date");
				}
			}
		}

		Medicine node = new Medicine(name, supplier_name, price, stock, location, expiry_date);
		node.accept_diseases();

		count++;
		root = insert(root, node);

	}
	// Time complexity: O(1)

	Medicine deleteNode(Medicine node, String name) {
		// PERFORM STANDARD BST DELETE
		if (node == null) {
			System.out.println("Medicine to be deleted not found");
			return node;
		}

		// If the key to be deleted is smaller than
		// the root's key, then it lies in left subtree
		if (name.compareTo(node.name) < 0)
			node.leftChild = deleteNode(node.leftChild, name);

		// If the key to be deleted is greater than the
		// root's key, then it lies in right subtree
		else if (name.compareTo(node.name) > 0)
			node.rightChild = deleteNode(node.rightChild, name);

		// if key is same as root's key, then this is the node
		// to be deleted
		else {

			// node with only one child or no child
			if ((node.leftChild == null) || (node.rightChild == null)) {
				Medicine temp = null;
				if (temp == node.leftChild)
					temp = node.rightChild;
				else
					temp = node.leftChild;

				// No child case
				if (temp == null) {
					temp = node;
					node = null;
				} else // One child case
					node = temp; // Copy the contents of
									// the non-empty child
			} else {

				// node with two children: Get the inorder
				// successor (smallest in the right subtree)
				Medicine temp = min(node.rightChild);

				// Copy the inorder successor's data to this node
				node.name = temp.name;
				node.supplier_name = temp.supplier_name;
				node.price = temp.price;
				node.stock = temp.stock;
				node.location = temp.location;
				node.expiry_date = temp.expiry_date;
				node.diseases = new ArrayList<String>();
				for (String dis : temp.diseases) {
					node.diseases.add(dis);
				}

				// Delete the inorder successor
				node.rightChild = deleteNode(node.rightChild, temp.name);
			}
		}

		// If the tree had only one node then return
		if (node == null)
			return node;

		// UPDATE HEIGHT OF THE CURRENT NODE
		node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;

		// GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = balance_factor(node);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && balance_factor(node.leftChild) >= 0)
			return rightRotate(node);

		// Left Right Case
		if (balance > 1 && balance_factor(node.leftChild) < 0) {
			node.leftChild = leftRotate(node.leftChild);
			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && balance_factor(node.rightChild) <= 0)
			return leftRotate(root);

		// Right Left Case
		if (balance < -1 && balance_factor(node.rightChild) > 0) {
			node.rightChild = rightRotate(node.rightChild);
			return leftRotate(node);
		}

		count--;
		System.out.println("Medicine successfully removed from inventory");
		System.out.println();
		return node;
	}

	// Finding minimum node
	Medicine min(Medicine node) {

		Medicine ptr = node;

		// Traversing left subtree
		while (ptr.leftChild != null) {
			ptr = ptr.leftChild;
		}

		// Returning leftmost node as it is min
		return ptr;
	}
	// Time complexity: O(h)...h is height if left subtree

	Medicine search_name(Medicine node, String name) {

		if (node == null) {
			System.out.println("Medicine  not found");
			return null;
		} else if (node.name.compareTo(name) == 0)
			return node;

		else if (node.name.compareTo(name) > 0) {
			return search_name(node.leftChild, name);
		} else {
			return search_name(node.rightChild, name);
		}

	}

	void checkStock() {
		Medicine ptr;
		int count = 0;
		// validation
		if (root == null) {
			System.out.println("store has no medicine!");
		} else {
			// implementation of queue using linked list
			Queue<Medicine> queue = new LinkedList<>();
			queue.add(root);
			queue.add(null); // adding null to find end of a level

			while (!queue.isEmpty()) {
				ptr = queue.poll();
				// if ptr is null that means we have added all next level nodes to queue so add
				// another null
				if (ptr == null) {
					// if it is last level no need to add null in queue
					if (!queue.isEmpty()) {
						queue.add(null);
					}
				} else {
					// add left child
					if (ptr.leftChild != null) {
						queue.add(ptr.leftChild);
					}
					// add right child
					if (ptr.rightChild != null) {
						queue.add(ptr.rightChild);
					}
					// print data whose stock is less
					if (ptr.stock < MinimumStock) {
						count++;
						System.out.println(count + "." + ptr.name + ":");
						System.out.println("\tStock:" + ptr.stock);
						System.out.println("\tSupplier:" + ptr.supplier_name);
					}

				}
			}
			System.out.println();
			System.out.println(count + " medicines need to be restocked");

		}
	}

	void checkExpiry() {
		Medicine ptr;
		int count = 0;
		Date currentDate = new Date();
		// validation
		if (root == null) {
			System.out.println("store has no medicine!");
		} else {
			// implementation of queue using linked list
			Queue<Medicine> queue = new LinkedList<>();
			queue.add(root);
			queue.add(null); // adding null to find end of a level

			while (!queue.isEmpty()) {
				ptr = queue.poll();
				// if ptr is null that means we have added all next level nodes to queue so add
				// another null
				if (ptr == null) {
					// if it is last level no need to add null in queue
					if (!queue.isEmpty()) {
						queue.add(null);
					}
				} else {

					// add left child
					if (ptr.leftChild != null) {
						queue.add(ptr.leftChild);
					}
					// add right child
					if (ptr.rightChild != null) {
						queue.add(ptr.rightChild);
					}
					// print current data
					if (ptr.expiry_date.before(currentDate)) {
						count++;
						System.out.println(count + "." + ptr.name + ":");
						System.out.println("\tStock:" + ptr.stock);
						System.out.println("\track number:" + ptr.location);
						System.out.println("\tSupplier:" + ptr.supplier_name);
					}

				}
			}
			System.out.println();
			System.out.println(count + " medicines are Expired");

		}
	}

	void restock() {
		Medicine node;
		System.out.print("Enter medicine to be restocked: ");
		String name = scan.next();

		node = search_name(root, name);

		if (node == null) {
			System.out.println("Do you want to add this medicine?");
			char ans = scan.next().charAt(0);
			if (ans == 'y' || ans == 'Y') {
				create();
			}
		} else {

			System.out.print("Enter the quantity added: ");
			int q = scan.nextInt();

			// expiry_date
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			boolean flag = false;

			Date expiry_date = null;

			System.out.println("Enter expiry date of new stock (dd/mm/yyyy)");
			while (!flag) {

				String cinput = scan.nextLine();
				if (null != cinput && cinput.trim().length() > 0) {
					try {
						expiry_date = format.parse(cinput);
						flag = true;
					} catch (ParseException e) {

						e.printStackTrace();
						System.out.println("Enter valid date");
					}
				}
			}

			node.stock += q;
			node.expiry_date = expiry_date;

		}
		System.out.println("Do you want to restock more medicines?");

		char ans = scan.next().charAt(0);

		if (ans == 'y' || ans == 'Y')

			restock();
	}

	void search_supplier_name(String supplier) {
		Medicine ptr;
		int count = 0;
		// validation
		if (root == null) {
			System.out.println("store has no medicine!");
		} else {
			// implementation of queue using linked list
			Queue<Medicine> queue = new LinkedList<>();
			queue.add(root);
			queue.add(null); // adding null to find end of a level

			while (!queue.isEmpty()) {
				ptr = queue.poll();
				// if ptr is null that means we have added all next level nodes to queue so add
				// another null
				if (ptr == null) {
					// if it is last level no need to add null in queue
					if (!queue.isEmpty()) {
						queue.add(null);
					}
				} else {
					// add left child
					if (ptr.leftChild != null) {
						queue.add(ptr.leftChild);
					}
					// add right child
					if (ptr.rightChild != null) {
						queue.add(ptr.rightChild);
					}
					// print data whose stock is less
					if (ptr.supplier_name.compareTo(supplier) == 0) {
						count++;
						System.out.println(count + "." + ptr.name + ":");
						System.out.println("\tStock:" + ptr.stock);
						System.out.println("\tSupplier:" + ptr.supplier_name);
					}

				}
			}
			System.out.println(count + " medicines are supplied by " + supplier);
		}
	}

	void search_disease(String disease) {
		Medicine ptr;
		int count = 0;
		// validation
		if (root == null) {
			System.out.println("store has no medicine!");
		} else {
			// implementation of queue using linked list
			Queue<Medicine> queue = new LinkedList<>();
			queue.add(root);
			queue.add(null); // adding null to find end of a level

			while (!queue.isEmpty()) {
				ptr = queue.poll();
				// if ptr is null that means we have added all next level nodes to queue so add
				// another null
				if (ptr == null) {
					// if it is last level no need to add null in queue
					if (!queue.isEmpty()) {
						queue.add(null);
					}
				} else {
					// add left child
					if (ptr.leftChild != null) {
						queue.add(ptr.leftChild);
					}
					// add right child
					if (ptr.rightChild != null) {
						queue.add(ptr.rightChild);
					}
					// print data whose stock is less
					if (ptr.diseases.contains(disease)) {
						count++;
						System.out.println(count + "." + ptr.name + ":");
						System.out.println("\tStock:" + ptr.stock);
						System.out.println("\track number:" + ptr.location);
						System.out.println("\tSupplier:" + ptr.supplier_name);
						System.out.println("\tDiseses that can be cured: ");
						System.out.println("\t" + ptr.diseases);

					}

				}
			}
			System.out.println(count + " medicines can cure " + disease);

		}
	}

	// Inorder
	void inorder_display(Medicine y) {

		// Return if tree is empty
		if (y == null) {
			return;
		}

		// Traverse left
		inorder_display(y.leftChild);
		// Print
		y.display();
		System.out.println();
		// Traverse right
		inorder_display(y.rightChild);
	}

	// Calling inorder on root
	void inorder_display() {
		inorder_display(root);
	}

//	void inorder(Medicine y) {
//		if (y == null) {
//			return;
//		}
//		// Traverse left
//		inorder_display(y.leftChild);
//		
//		
//		// Traverse right
//		inorder_display(y.rightChild);
//	}

	void customer_order(Customer c) {

		char ans;

		System.out.println("Enter the medicines you want to purchase:");
		do {
			Purchased p = new Purchased();
			p.accept();
			Medicine m = search_name(root, p.medicine_name);
			if (m != null) {
				if (m.stock < p.quantity) {
					System.out.println("Stock available is " + m.stock + " only");
				} else {
					p.sell_price = m.price;
					c.purchased.add(p);
				}

			}
			System.out.println("Do you want to purchase more?");
			ans = scan.next().charAt(0);
		} while (ans == 'y' || ans == 'Y');

	}

	void search_byname(Medicine node) {

		System.out.println("Enter the name of the medicine you want to search");
		String name = scan.next();
		Medicine med = search_name(node, name);

		if (med != null)
			med.display();
	}

}
