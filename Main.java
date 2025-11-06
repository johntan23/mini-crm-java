public class Main {
    public static void main(String[] args) {

        // Create Customers
        Customer customer1 = new Customer(1, "John Doe", "jd@gmail.com", "6981234567");
        Customer customer2 = new Customer(2, "Jane Smith","js@yahoo.com", "6998765432");

        // Create products
        Product product1 = new Product(1,"Laptop", 950.00, 10);
        Product product2 = new Product(2, "Mouse", 25.50, 50);
        Product product3 = new Product(3, "Keyboard", 45.00, 30);

        // John's order creation
        Order order1 = new Order(customer1);
        order1.addProduct(product1, 0);  // 2 laptops
        order1.addProduct(product2, 1);  // 1 mouse

        // Jane's order creation
        Order order2 = new Order(customer2);
        order2.addProduct(product2, 3);  // 3 mice
        order2.addProduct(product3, 2);  // 2 keyboards

        // Show orders
        System.out.println(order1);
        System.out.println();   
        System.out.println(order2);

        // Show new stock
        System.out.println("\nUpdated Product Stock:");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
    }
}