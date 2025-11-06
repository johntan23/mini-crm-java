import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer; 
    private List<OrderItem> items = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) { 
            total += item.getTotal(); 
        }
        return total;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for ").append(customer.getName()).append(":\n");
        sb.append(String.format("%-15s | %-8s | %-10s\n", "Product", "Quantity", "Total"));
        sb.append("----------------------------------\n");
        for (OrderItem item : items) { 
            sb.append(String.format("%-15s | %-8d | %-10.2f€\n", 
            item.getProduct().getName(), 
            item.getQuantity(), 
            item.getTotal()));
        }
        return sb.toString();
    }
}