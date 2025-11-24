class OrderItem {
    private Product product;
    private int quantity;

    // Constructor links a product and a quantity to this order item
    public OrderItem(Product product, int quantity) {
        if (quantity <= 0) { 
            throw new IllegalArgumentException("Quantity must be greater than 0"); 
        }
        if (quantity > product.getStock()) { 
            throw new IllegalArgumentException("Not enough stock for product: " + product.getName()); 
        }
        this.product = product;
        this.quantity = quantity;
        product.reduceStock(quantity); // Reduce stock immediately upon adding to order
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    // Returns total price for this item (price * quantity)
    public double getTotal() { return product.getPrice() * quantity; }

    /*@Override
    public String toString() {
        return  product.getName() + " | Quantity: " + quantity + " | Total: " + String.format("%.2f€", getTotal());
    }*/
}

// OrderItem represents a single product with a specified quantity in an order

// Constructor performs validation:
// - quantity must be > 0
// - quantity cannot exceed product stock
// Immediately reduces the product's stock when the item is created

// getTotal computes the total cost of this item

// Optional toString provides formatted output for debugging