package medical_Store_Management;

import java.util.HashMap;
import java.util.Map;

public class CustomerHash {

	static HashMap<String, Customer> customerDatabase = new HashMap<>();
	
	static void insert(Customer new_customer){
		
		customerDatabase.put(new_customer.id, new_customer);
		
	}
	
	static void  displayTotalSales(){
		
		System.out.println("Total transactions till date");
		int sales = 0;
		Customer value;
		//tagline
		System.out.println("Customer id\tName\tBill (Rs.)");
		
		for ( @SuppressWarnings("rawtypes") Map.Entry mapElement : customerDatabase.entrySet()) {
			
    		value = (Customer)mapElement.getValue();
    		
			sales+=value.bill;
			System.out.println(value.id +"\t"+value.name+"\t"+ value.bill);
        }
		System.out.println("Total revenue generated:\t\t(Rs.)" + sales);
	}
	
	
	
	
}
