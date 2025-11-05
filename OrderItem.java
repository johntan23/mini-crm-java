import java.util.ArrayList;
import java.util.List;

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        product.reduceStock(quantity); 
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return  product.getName() + " | Quantity: " + quantity + " | Total: " + getTotal();
    }
}