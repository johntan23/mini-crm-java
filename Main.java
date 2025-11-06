import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CRMSystem crm = new CRMSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n======= CRM Console Menu =======");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Create Order");
            System.out.println("4. Show Customers");
            System.out.println("5. Show Products");
            System.out.println("6. Show Orders");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Customer ID: ");
                    int cid = sc.nextInt(); 
                    sc.nextLine();
                    System.out.print("Customer Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Customer Email: ");
                    String cemail = sc.nextLine();
                    System.out.print("Customer Phone: ");
                    String cphone = sc.nextLine();
                    try {
                        crm.addCustomer(new Customer(cid, cname, cemail, cphone));
                        System.out.println("Customer added!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Product ID: ");
                    int pid = sc.nextInt(); 
                    sc.nextLine();
                    System.out.print("Product Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Product Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Product Stock: ");
                    int stock = sc.nextInt(); 
                    sc.nextLine();
                    try {
                        crm.addProduct(new Product(pid, pname, price, stock));
                        System.out.println("Product added!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Customer ID for the order: ");
                    int orderCid = sc.nextInt(); 
                    sc.nextLine();
                    Customer orderCustomer = crm.findCustomerById(orderCid);

                    if (orderCustomer == null) {
                        System.out.println("Customer not found!");
                        break;
                    }

                    Order order = new Order(orderCustomer);
                    int prodId;
                    do {
                        System.out.print("Product ID to add (0 to finish): ");
                        prodId = sc.nextInt(); 
                        sc.nextLine();
                        if (prodId == 0) break;

                        Product product = crm.findProductById(prodId);
                        if (product == null) {
                            System.out.println("Product not found!");
                            continue;
                        }

                        System.out.print("Quantity: ");
                        int qty = sc.nextInt(); 
                        sc.nextLine();

                        try {
                            order.addProduct(product, qty);
                            System.out.println("Product added to order!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } while (prodId != 0);

                    if (order.getItems().isEmpty()) {
                        System.out.println("No items added. Order cancelled.");
                    } else {
                        crm.addOrder(order);
                        System.out.println("Order created!");
                    }
                    break;

                case 4:
                    crm.showCustomers();
                    break;

                case 5:
                    crm.showProducts();
                    break;

                case 6:
                    crm.showOrders();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
