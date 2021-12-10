## Mini – Project

## Medical Store Management System
 
### Contributors:
      	2303 Aparna Agrawal  
        2312 Hardiki Sonchhatra  
        2317 Khushbu Bora  
        2326 Sudha Chintake  
          
### Details:
        This application has been built to reduce manual work of medical stores. Objective is to organize medical store details like medicines stocks, location, transactions  
        This project has been designed for both admin and customer interface.  
          
### Data Structure and Algorithm Used
#### Medical Inventory: Avl Tree  
    - Created on the basis of names of medicines
    - Time complexity of searching is reduced to O(logn)
    -  No extra static memory space required
#### Customer Database: HashTable
    - Implemented using java hashmap
    - Maps key (customer id) to our value (corresponding customer object)
    - Insertion time complexity reduced to O (1)

### Classes
    1.	Medicine (Implements serializable)  
        a.	Parameterized Constructor: Set values of instance variables.  
        b.	void accept_diseases (): Accept disease ArrayList.  
        c.	void display (): Display medicine details.  
    2.	Medical_avl
        a.	Constructor: Set root to null.  
        b.	private int height (Medicine m): Returns height of m.  
        c.	private int balance_factor (Medicine m): Calculates balance factor.  
        d.	private Medicine rightRotate (Medicine m): Perform right rotation around m.  
        private Medicine leftRotate (Medicine m): Performs left rotation around m.  
        e.	Medicine insert (Medicine root, Medicine m): Add m to the root  
        void create (): Create and accepts medicine details and add it to root using insert method.  
        f.	void inorder_display (Medicine m): Display medicine recursively in inorder fashion.  
        void inorder_display (): Calls inorder_display (Medicine m) by passing root.  
        g.	void check_stock (): Display required details of out-of-stock medicines.  
        h.	void checkExpiry (): Display required details of out-of-stock medicines.  
        i.	Medicine search_name (Medicine m, String n): Recursively search and returns Medicine named n.  
        void search_by_name (Medicine root): Calls search_name by passing root and display its details.  
        j.	void search_disease (String d): Display required details of medicines which cure disease d.  
        k.	void search_supplier_name (String s): Display required details of medicines supplied by supplier s.  
        l.	void restock (): Restock medicines  
        m.	 Medicine deleteNode (Medicine root, String n): Recursively search and delete medicine named n from tree.  
        n.	private Medicine min (Medicine m): Returns minimum node.  
        o.	void customer_order (Customer c): Takes input for purchased list of Customer c and updates stock of purchased medicines.  
    3.	Purchased (implements Serializable)  
        a.	void accept (): Accept details of medicine to be purchased.  
        b.	void display (): Display details of the purchased medicine.  
    4.	Customer (implements Serializable)  
        a.	void accept_customer (): Accepts the details of customer.  
        b.	void display (): Display customer details.  
        c.	void create_customer_bill (): Adds up all the prices of purchased medicine and display bill.  
        d.	void update_quantity_of_medicine (Medical_avl m): Updates the quantity of medicine in purchased list and updates stock of purchased medicines.  
        e.	void remove_medicine_from_bill (Medical_avl m): Removes medicine from purchased list and updates stock of removed medicines.  
    5.	CustomerHash  
        a.	static void insert (Customer c): Put customer c into static hashmap customerDatabase (consider c’s id as key).  
        b.	static void displayTotalSales (): Traverse through customerDatabase and display id, name and total bill of each customer and adds bill to display total revenue generated.  
    6.	MedicalMain  
        a.	static void writeToFile (Medical_avl m): Writes root of m to the file.   
        static void writeToFile (): Writes all customer object present in customer database in the file.  
        b.	static void ReadFromFile (Medical_avl m): Reads object from the file to root of m.  
        static void ReadFromFile (): Reads each customer object and puts it into a static hashmap customerDatabase.  
        c.	public static void main (String args[]): Main method from where program execution starts.  
            
### Future Scope  
    •	Customer Transaction History could be stored.  
    •	Method to display day to day transaction of medical store could be added.  
    •	This application can be implemented for any other store or multiple stores by creating multiple instances of tree.  

### References  
•	DSAII unit 2 and unit 6 pdf  
•	https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/#:~:text=HashMap%20is%20similar%20to%20the,util.  
•	https://www.geeksforgeeks.org/avl-tree-set-1-insertion/  
