import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer; 
    private List<OrderItem> items = new ArrayList<>();

    // Constructor links an order to a specific customer
    public Order(Customer customer) {
        this.customer = customer;
    }

    // Adds a product to the order by creating a new OrderItem
    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity)); // OrderItem constructor handles stock reduction and validation
    }

    // Calculate total cost of the order
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) { 
            total += item.getTotal(); 
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items); // Return unmodifiable copy to prevent external modification
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for ").append(customer.getName()).append(":\n");
        sb.append(String.format("%-15s | %-8s | %-10s\n", "Product", "Quantity", "Total"));
        sb.append("-----------------------------------------------------------------------\n");
        for (OrderItem item : items) { 
            sb.append(String.format("%-15s | %-8d | %-10.2f€\n", 
            item.getProduct().getName(), 
            item.getQuantity(), 
            item.getTotal()));
        }
        return sb.toString();
    }
}

// Order class links a customer to a list of purchased products (OrderItems)

// addProduct: creates a new OrderItem which automatically reduces product stock
// and validates quantity. Prevents invalid orders or negative stock.

// getTotal: calculates sum of all item totals in the order

// getItems returns an unmodifiable copy of the order items to protect internal state

// toString: nicely formats order details for console output or debugging