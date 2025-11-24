import java.util.ArrayList;
import java.util.List;

public class CRMSystem {

    // Core in-memory storage for all entities managed by the system.
    private List<Customer> customers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    // Return unmodifiable copies to prevent external classes from modifying internal lists
    public List<Customer> getCustomers() { return List.copyOf(customers); }
    public List<Product> getProducts() { return List.copyOf(products); }
    public List<Order> getOrders() { return List.copyOf(orders); }

    public void addCustomer(Customer c) { 
        if (c == null) throw new IllegalArgumentException("Customer cannot be null");

        // Ensures uniqueness of customer IDs (simple validation, no database)
        if (findCustomerById(c.getId()) != null) {
            throw new IllegalArgumentException("Customer ID already exists");
        }
        customers.add(c); 
    }

    public void addProduct(Product p) { 
        if (p == null) throw new IllegalArgumentException("Product cannot be null");

        // Prevent adding products with duplicate IDs
        if (findProductById(p.getId()) != null) {
            throw new IllegalArgumentException("Product ID already exists");
        }
        products.add(p); 
    }

    // Orders don’t require duplicate checks since ID is not used here
    public void addOrder(Order o) { 
        orders.add(o); 
    }

    // Console debug helper — not used by the GUI, but useful for testing
    public void showCustomers() {
        System.out.println("================= Customers =================");
        for (Customer c : customers) System.out.println(c);
    }

    public void showProducts() {
        System.out.println("================= Products =================");
        for (Product p : products) System.out.println(p);
    }

    public void showOrders() {
        System.out.println("================= Orders =================");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // Linear search – acceptable for small data sets in a simple project; not optimized.
    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    // Same simple lookup for products
    public Product findProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}