import java.util.ArrayList;
import java.util.List;

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if (quantity <=0) { throw new IllegalArgumentException("Quantity must be greater than 0"); }
        if (quantity > product.getStock()) { throw new IllegalArgumentException("Not enough stock for product: " + product.getName()); }
        this.product = product;
        this.quantity = quantity;
        product.reduceStock(quantity); 
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return  product.getName() + " | Quantity: " + quantity + " | Total: " + String.format("%.2f€", getTotal());
    }
}