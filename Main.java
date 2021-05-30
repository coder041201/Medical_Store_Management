import java.util.Scanner;
import java.util.HashMap;
public class MedicalMain {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        HashMap<String, Customer> customerDatabase = new HashMap<>();
        Medical_avl medavl=new Medical_avl();
        int ch,choice;
        do{
            //Welcome msg
            System.out.println("Choose 0 if you are the Admin. \nChoose 1 if you are a Customer.");
            System.out.println("Enter anything else to EXIT.");
            choice=sc.nextInt();
                if(choice==0){
                    do{
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
                        ch=sc.nextInt();
                        switch(ch){
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
                            case 6:
                            {
                                System.out.println("Enter disease you want to search the medicine for:");
                                String disease=sc.next();
                                medavl.search_disease(disease);
                            }

                            break;
                            case 7:
                            {
                                System.out.println("Enter supplier name you want to search:");
                                String supplier=sc.next();
                                medavl.search_supplier_name(supplier);
                            }

                            break;
                            case 8:
                                medavl.restock();
                                break;
                            case 9:
                            {
                                System.out.println("Enter name you want to delete:");
                                String name=sc.next();
                                medavl.deleteNode(medavl.root, name);
                            }
                            break;
                            case 10:
                                medavl.displayTotalSales();
                                break;
                            case 11:
                                System.out.println("Exiting..");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                    }while(ch!=11);


            }
                else if(choice==1){
                    Customer new_customer = new Customer();
                    do{
                        System.out.println("*Customer Menu*");
                        System.out.println("[1] Accept and Display the Details");
                        System.out.println("[2] Make a new Bill");
                        System.out.println("[3] Check-out");
                        System.out.println("[4] Remove a medicine from the bill");
                        System.out.println("[5] Update the quantity of a medicine");
                        System.out.println("Enter your choice.");
                        ch=sc.nextInt();

                        switch(ch){
                            case 1:
                                new_customer.accept_customer();
                                medavl.customer_order(new_customer);
                                new_customer.display();
                                break;
                            case 2:
                                medavl.customer_order(new_customer);
                                break;
                            case 3:
                                new_customer.create_customer_bill();
                                break;
                            case 4:
                                new_customer.remove_medicine_from_bill();
                                break;
                            case 5:
                                new_customer.update_Quantity_of_medicine();
                                break;
                            case 6:
                                System.out.println("Thank You for Visiting our Store:)");
                        }
                    }while(ch!=6);

                }
        }while(choice!=3);

    }
}
