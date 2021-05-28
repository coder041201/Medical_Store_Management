package miniProject;

import java.util.HashMap;

public class CustomerHash {

	HashMap<String, Customer> customerDatabase = new HashMap<>();
	
	void insert(Medical_avl  m){
		Customer new_customer = m.customer_order();
		customerDatabase.put(new_customer.id, new_customer);
	}
}
