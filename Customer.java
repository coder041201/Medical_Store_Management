package miniProject;

import java.util.ArrayList;
import java.util.Scanner;

class Purchased {
	
	Scanner sc = new Scanner(System.in); 
	
	String medicine_name;
	int quantity;
	
	void accept() {
		System.out.print("Enter medicine name: ");
		medicine_name = sc.next();
		System.out.print("Enter Quantity: ");
		quantity = sc.nextInt();
	}
	
	void display() {
		System.out.println(medicine_name + ": " + quantity);
	}
}

public class Customer {

	Scanner sc = new Scanner(System.in);

	String id;
	String name;
	Long contact_no;
	String prescribed_by;
	ArrayList<Purchased> purchased;
	int bill;

	Customer() {

	}

	Customer(String id, String name, Long contact_no, String prescribed_by, int bill) {

		this.id = id;
		this.name = name;
		this.contact_no = contact_no;
		this.prescribed_by = prescribed_by;
		this.bill = bill;

	}

	void accept() {
		System.out.println("Enter Customer id");
		id = sc.next();
		System.out.print("Enter name: ");
		name = sc.next();
		System.out.print("Enter contact no.: ");
		contact_no = sc.nextLong();
		System.out.print("Enter name of doctor: ");
		prescribed_by = sc.next();
		System.out.println();
	}


	void display() {
		System.out.println("customer id: " + id);
		System.out.println("name: " + name);
		System.out.println("contact number:" + contact_no);
		System.out.println("Prescribed by:" + prescribed_by);
		System.out.println("Medicines purchased: ");
		for(Purchased p : purchased) {
			p.display();
		}
		System.out.println("Total Bill:" + bill);
	}
	
	void accept_order() {
		
	}
	
}
