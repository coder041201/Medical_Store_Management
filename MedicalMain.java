package medical_Store_Management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class MedicalMain {

	static void writeToFile(Medical_avl medavl) {
		try {
			FileOutputStream f = new FileOutputStream("MedicineInventory.txt");
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			int c = medavl.size();
			o.writeInt(c);

			if (medavl.root == null) {
				System.out.println("No medicine present");
				f.close();
				o.close();
				return;
			}

			Stack<Medicine> s = new Stack<Medicine>();

			// Traversing tree
			Medicine ptr = medavl.root;

			// Pushing left tree in stack
			while (ptr != null || !s.empty()) {
				while (ptr != null) {
					s.push(ptr);
					ptr = ptr.leftChild;
				}

				// Printing leftmost node
				ptr = s.pop();
				o.writeObject(ptr);

				// Set ptr to right to traverse right tree
				ptr = ptr.rightChild;
			}
			o.close();
			f.close();
			System.out.println("data written successfully");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

	static void writeToFile() {
		try {
			FileOutputStream f = new FileOutputStream(new File("CustomerDetails.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeInt(CustomerHash.customerDatabase.size());

			for (@SuppressWarnings("rawtypes")
			Map.Entry mapElement : CustomerHash.customerDatabase.entrySet()) {

				o.writeObject((Customer) mapElement.getValue());

			}

			o.close();
			f.close();
			System.out.println("data written successfully");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

	static void ReadFromFile(Medical_avl medavl) {
		try {
			FileInputStream finput = new FileInputStream("MedicineInventory.txt");
			ObjectInputStream oinput = new ObjectInputStream(finput);

			int length = oinput.readInt();
			for (int i = 0; i < length; i++) {
				medavl.insert(medavl.root, (Medicine) oinput.readObject());
			}
			oinput.close();
			finput.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static void ReadFromFile() {
		try {
			FileInputStream finput = new FileInputStream(new File("CustomerDetails.txt"));
			ObjectInputStream oinput = new ObjectInputStream(finput);

			int length = oinput.readInt();
			for (int i = 0; i < length; i++) {
				CustomerHash.insert((Customer) oinput.readObject());
			}
			oinput.close();
			finput.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// CustomerHash customerDetails = new CustomerHash();
		Medical_avl medavl = new Medical_avl();
		ReadFromFile(medavl);
		ReadFromFile();

		int ch, choice;

		do {
			// Welcome msg
			System.out.println("Choose 0 if you are the Admin. \nChoose 1 if you are a Customer.");
			System.out.println("Enter anything else to EXIT.");
			choice = sc.nextInt();
			if (choice == 0) {
				do {
					System.out.println("-----------------MENU-----------------");
					System.out.println("[1] Add a new Medicine");
					System.out.println("[2] Display the Inventory");
					System.out.println("[3] Check the Stock of a medicine");
					System.out.println("[4] Check the Expiry Date of a medicine in stock");
					System.out.println("[5] Search a medicine by Name");
					System.out.println("[6] Search a medicine by Disease for which the medicine is required");
					System.out.println("[7] Search the medicine by the Supplier Name");
					System.out.println("[8] Restock a Medicine");
					System.out.println("[9] Remove a Medicine from the Inventory");
					System.out.println("[10] Display total sales");
					System.out.println("[11] Exit the Menu");
					System.out.println("Enter your choice.");
					ch = sc.nextInt();
					switch (ch) {
					case 1:
						medavl.create();
						break;
					case 2:
						medavl.inorder_display();
						break;
					case 3:
						medavl.checkStock();
						break;
					case 4:
						medavl.checkExpiry();
						break;
					case 5:
						medavl.search_byname(medavl.root);
						break;
					case 6: {
						System.out.println("Enter disease you want to search the medicine for:");
						String disease = sc.next();
						medavl.search_disease(disease);
					}

						break;
					case 7: {
						System.out.println("Enter supplier name you want to search:");
						String supplier = sc.next();
						medavl.search_supplier_name(supplier);
					}

						break;
					case 8:
						medavl.restock();
						break;
					case 9: {
						System.out.println("Enter name you want to delete:");
						String name = sc.next();
						medavl.deleteNode(medavl.root, name);
					}
						break;
					case 10:
						CustomerHash.displayTotalSales();
						break;
					case 11:
						System.out.println("Exiting..");
						writeToFile(medavl);
						break;
					default:
						System.out.println("Invalid choice.");
						break;
					}
				} while (ch != 11);

			} else if (choice == 1) {
				Customer new_customer = new Customer();
				do {
					System.out.println();
					System.out.println("***Customer Menu***");
					System.out.println("[1] Accept and Display the Details");
					System.out.println("[2] Place order");
					System.out.println("[3] Remove a medicine from the bill");
					System.out.println("[4] Update the quantity of a medicine");
					System.out.println("[5] Display bill");
					System.out.println("[6] Check out");
					System.out.println();
					System.out.println("Enter your choice.");
					ch = sc.nextInt();

					switch (ch) {
					case 1:
						new_customer.accept_customer();
						// medavl.customer_order(new_customer);
						new_customer.display();
						CustomerHash.insert(new_customer);
						break;
					case 2:
						medavl.customer_order(new_customer);
						break;
					case 3:
						new_customer.remove_medicine_from_bill();
						break;
					case 4:
						new_customer.update_Quantity_of_medicine();
						break;
					case 5:
						new_customer.create_customer_bill();
						break;
					case 6:
						System.out.println("Thank You for Visiting our Store:)");
						writeToFile();
						break;
					default:
						System.out.println("Invalid choice!!!");
						break;
					}
				} while (ch != 6);

			}
		} while (choice ==0 || choice==1);

		sc.close();

	}
}
