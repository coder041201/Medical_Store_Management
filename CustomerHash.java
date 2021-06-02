package medical_Store_Management;

import java.util.HashMap;
import java.util.Map;

//Customer Hash class
public class CustomerHash {

	//Creating hashmap
	static HashMap<Integer, Customer> customerDatabase = new HashMap<>();
	
	//static id
	static int curr_id = 1234;
	
	//inserting customer to hashmap
	static void insert(Customer new_customer){
		customerDatabase.put(new_customer.id, new_customer);	
	}
	
	//display total sales
	static void  displayTotalSales(){
		
		System.out.println("Total transactions till date");
		int sales = 0;
		Customer value;
		//tagline
		System.out.println("Customer id\tName\t  Bill (Rs.)");
		
		//traverse through hashmap
		for ( @SuppressWarnings("rawtypes") Map.Entry mapElement : customerDatabase.entrySet()) {	
    		value = (Customer)mapElement.getValue();
    		
			//add sales
			sales+=value.bill;
			
			//print details
			System.out.println(value.id +"\t\t"+value.name+"\t  "+ value.bill);
        }
		System.out.println("Total revenue generated: (Rs.)" + sales);
	}
}
