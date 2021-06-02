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

//Main class
public class MedicalMain {

	// Writing medical inventory to file
	static void writeToFile(Medical_avl medavl) {
		try {

			// Initializing file output stream
			FileOutputStream f = new FileOutputStream("MedicineInventory.txt");

			// Initializing object output stream
			ObjectOutputStream o = new ObjectOutputStream(f);

			// if root is null
			if (medavl.root == null) {

				// display message
				System.out.println("No medicine present");

				// close streams
				f.close();
				o.close();

				// return
				return;
			}

			// else write root to file
			o.writeObject(medavl.root);

			// close stream
			o.close();
			f.close();
		}

		// catch exceptions if any
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

	// Writing customer objects to file
	static void writeToFile() {
		try {

			// Initializing file output stream
			FileOutputStream f = new FileOutputStream(new File("CustomerDetails.txt"));

			// Initializing object output stream
			ObjectOutputStream o = new ObjectOutputStream(f);

			// write static id to file (so we can continue from id where we left)
			o.writeInt(CustomerHash.curr_id);

			// size of hashmap
			o.writeInt(CustomerHash.customerDatabase.size());

			// traverse through hashmap
			for (@SuppressWarnings("rawtypes")
			Map.Entry mapElement : CustomerHash.customerDatabase.entrySet()) {

				// Write each customer objects to file
				o.writeObject((Customer) mapElement.getValue());

			}

			// close streams
			o.close();
			f.close();
		}

		// catch exceptions if any
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

	// Reading medical inventory from file
	static void ReadFromFile(Medical_avl medavl) {
		try {

			// Initializing file input stream
			FileInputStream finput = new FileInputStream("MedicineInventory.txt");

			// Initializing object input stream
			ObjectInputStream oinput = new ObjectInputStream(finput);

			// reading root
			medavl.root = medavl.insert(medavl.root, (Medicine) oinput.readObject());

			// closing streams
			oinput.close();
			finput.close();

			// catch exceptions if any
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Reading customer objects from file
	static void ReadFromFile() {
		try {

			// Initializing file output stream
			FileInputStream finput = new FileInputStream(new File("CustomerDetails.txt"));

			// Initializing object output stream
			ObjectInputStream oinput = new ObjectInputStream(finput);

			// read static id from file (so we can continue from id where we left)
			CustomerHash.curr_id = oinput.readInt();

			// read length
			int length = oinput.readInt();

			// read one by one customer object and put into hashmap
			for (int i = 0; i < length; i++) {
				CustomerHash.insert((Customer) oinput.readObject());
			}

			// close strams
			oinput.close();
			finput.close();

			// catch exceptions if any
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// main
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// creating avl tree obj
		Medical_avl medavl = new Medical_avl();

		// reading data from files
		ReadFromFile(medavl);
		ReadFromFile();

		int ch, choice;

		do {
			// Welcome msg
			System.out.println("Choose 0 if you are the Admin. \nChoose 1 if you are a Customer.");

			// asking user choice
			System.out.println("Enter anything else to EXIT.");
			choice = sc.nextInt();

			// Admin
			if (choice == 0) {
				do {

					// Displayin admin menu
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

					// Accepting user choice
					System.out.println("Enter your choice.");
					ch = sc.nextInt();

					// Switch case
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
						System.out.println("Medicine successfully removed from inventory");
						System.out.println();
					}
						break;
					case 10:
						CustomerHash.displayTotalSales();
						break;
					case 11:
						System.out.println("Exiting..");

						// write all medical inventory data back to file
						writeToFile(medavl);
						break;
					default:
						System.out.println("Invalid choice.");
						break;
					}
				} while (ch != 11);
			}

			// Customer
			else if (choice == 1) {

				// create customer obj
				Customer new_customer = new Customer();

				// input its details
				new_customer.accept_customer();

				// insert it into hash table
				CustomerHash.insert(new_customer);

				do {

					// display customer menu
					System.out.println();
					System.out.println("***Customer Menu***");
					System.out.println("[1] Display the Details");
					System.out.println("[2] Place order");
					System.out.println("[3] Create bill");
					System.out.println("[4] Update the quantity of a medicine");
					System.out.println("[5] Remove a medicine from the bill");
					System.out.println("[6] Check out");
					System.out.println();

					// ask user choice
					System.out.println("Enter your choice.");
					ch = sc.nextInt();

					// Switch cases
					switch (ch) {
					case 1:
						new_customer.display();
						break;
					case 2:
						medavl.customer_order(new_customer);
						break;
					case 3:
						new_customer.create_customer_bill();
						break;
					case 4:
						new_customer.update_Quantity_of_medicine(medavl);
						break;
					case 5:
						new_customer.remove_medicine_from_bill(medavl);
						break;
					case 6:
						System.out.println("Thank You for Visiting our Store:)");

						// writing back customer details to file
						writeToFile();
						break;
					default:
						System.out.println("Invalid choice!!!");
						break;
					}
				} while (ch != 6);
			}
		} while (choice == 0 || choice == 1);
		sc.close();
	}
}
