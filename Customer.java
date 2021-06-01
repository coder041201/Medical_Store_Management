package medical_Store_Management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Purchased implements Serializable {
	
	
	
	String medicine_name;
	int quantity;
	int sell_price;
	
	void accept() {
		Scanner sc = new Scanner(System.in); 
		
		System.out.print("Enter medicine name: ");
		medicine_name = sc.next();
		System.out.print("Enter Quantity: ");
		quantity = sc.nextInt();
	}
	
	void display() {
		System.out.println(medicine_name + "\t  " + quantity + "\t\t" +sell_price*quantity);
	}
}

public class Customer implements Serializable{

	
	
	

	int id;
	String name;
	Long contact_no;
	String prescribed_by;
	ArrayList<Purchased> purchased;
	String address;
	int bill;

	Customer() {

	}

	Customer(int id, String name, Long contact_no, String prescribed_by, int bill , String address) {

		this.id = id;
		this.name = name;
		this.contact_no = contact_no;
		this.prescribed_by = prescribed_by;
		this.bill = bill;
		this.address = address;

	}

	void accept_customer() {
		
		Scanner sc = new Scanner(System.in);
		
		id = CustomerHash.curr_id;
		
		CustomerHash.curr_id ++;
		
		System.out.println("Your customer id for this purchase is: " + id);
		System.out.print("Enter name: ");
		name = sc.next();
		System.out.print("Enter contact no.: ");
		contact_no = sc.nextLong();
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
		System.out.println("Total Bill\t\t "+"Rs: "+bill+" only");
		System.out.println("*********************************");

	}
	
	void remove_medicine_from_bill(Medical_avl root) {
		
		Scanner sc = new Scanner(System.in);
		
		String med_name;
		int flag = 0;
		System.out.print("Enter medicine to remove: ");
		med_name = sc.next();
		Medicine node = root.search_name(root.root, med_name);
		for(Purchased p : purchased) {
			if(p.medicine_name.equalsIgnoreCase( med_name)){
				bill-=p.sell_price*p.quantity;
				node.stock = node.stock + p.quantity;
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
	
	void update_Quantity_of_medicine(Medical_avl root) {
		
		Scanner sc = new Scanner(System.in);
		
		String med_name;
		int new_quantity;
		int flag = 0;
		System.out.print("Enter medicine whose quantity is to be updated: ");
		med_name = sc.next();
		Medicine node = root.search_name(root.root, med_name);
		for(Purchased p : purchased) {
			if(p.medicine_name.equalsIgnoreCase( med_name)){
				System.out.print("Enter new quantity: ");
				new_quantity = sc.nextInt();
				node.stock = node.stock + p.quantity - new_quantity;
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
