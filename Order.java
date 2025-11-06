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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for ").append(customer.getName()).append(":\n");
        for (OrderItem item : items) { 
            sb.append("  - ").append(item).append("\n"); 
        }
        sb.append("Total Order Cost: ").append(String.format("%.2f€", getTotal()));
        return sb.toString();
    }
    
}