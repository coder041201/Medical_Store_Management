package medical_Store_Management;

import java.util.HashMap;

public class CustomerHash {

	HashMap<String, Customer> customerDatabase = new HashMap<>();
	
	void insert(Medical_avl  m){
		
		Customer new_customer = new Customer();
		new_customer.accept();
		
		m.customer_order(new_customer);
		
		customerDatabase.put(new_customer.id, new_customer);
	}
}
