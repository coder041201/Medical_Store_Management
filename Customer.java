package medical_Store_Management;

import java.util.ArrayList;
import java.util.Scanner;

class Purchased {
	
	Scanner sc = new Scanner(System.in); 
	
	String medicine_name;
	int quantity;
	int sell_price;
	
	void accept() {
		System.out.print("Enter medicine name: ");
		medicine_name = sc.next();
		System.out.print("Enter Quantity: ");
		quantity = sc.nextInt();
	}
	
	void display() {
		System.out.println(medicine_name + "\t  " + quantity +"\t  "+sell_price*quantity);
	}
}

public class Customer {

	Scanner sc = new Scanner(System.in);
	
	static String curr_id = "aaa";

	String id;
	String name;
	Long contact_no;
	String prescribed_by;
	ArrayList<Purchased> purchased;
	String address;
	int bill;

	Customer() {

	}

	Customer(String id, String name, Long contact_no, String prescribed_by, int bill , String address) {

		this.id = id;
		this.name = name;
		this.contact_no = contact_no;
		this.prescribed_by = prescribed_by;
		this.bill = bill;
		this.address = address;

	}

	void accept_customer() {
		
		id = curr_id;
		
		curr_id = (String)(curr_id + 1);
		
		System.out.println("Your customer id for this purchase is: " + id);
//		id = sc.next();
		System.out.print("Enter name: ");
		name = sc.next();
		System.out.print("Enter contact no.: ");
		contact_no = sc.nextLong();
		//sc.next();
		System.out.println("Enter address");
		sc.next();
		address = sc.nextLine();
		System.out.print("Enter name of doctor: ");
		prescribed_by = sc.next();
		System.out.println();
		
		purchased = new ArrayList<Purchased>();
	}


	void display() {
		System.out.println("Customer id: " + id);
		System.out.println("Name: " + name);
		System.out.println("Contact number:" + contact_no);
		System.out.println("Customer address: " + address);
		System.out.println("Prescribed by:" + prescribed_by);
	}
	
	void create_customer_bill() {
		
		bill=0; 
		System.out.println("*********************************");
		//tag line
		System.out.println("Medicine\tQuantity\tPrice");
		for(Purchased p : purchased) {
			p.display();
			bill+=p.sell_price*p.quantity;
		}
		System.out.println("Total Bill\t\t\t "+"Rs: "+bill+" only");
		System.out.println("*********************************");

	}
	
	void remove_medicine_from_bill() {
		String med_name;
		int flag = 0;
		System.out.print("Enter medicine to remove: ");
		med_name = sc.next();
		for(Purchased p : purchased) {
			if(p.medicine_name.equalsIgnoreCase( med_name)){
				bill-=p.sell_price*p.quantity;
				purchased.remove(p);
				System.out.println("medicine removed from bill");
				flag =1;
				break;
			}
		}
		if(flag == 0){
			System.out.println("medicine not purchased");
		}
	}
	
	void update_Quantity_of_medicine() {
		String med_name;
		int new_quantity;
		int flag = 0;
		System.out.print("Enter medicine whose quantity is to be updated: ");
		med_name = sc.next();
		for(Purchased p : purchased) {
			if(p.medicine_name.equalsIgnoreCase( med_name)){
				System.out.print("Enter new quantity: ");
				new_quantity = sc.nextInt();
				p.quantity = new_quantity;
				bill+=p.sell_price*(new_quantity-p.quantity);
				System.out.println("quantity updated Successfully");
				flag =1;
				break;
			}
		}
		if(flag == 0){
			System.out.println("medicine not purchased");
		}
	}

	
}
