public class Product {
    private final int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        if (price <=0) { throw new IllegalArgumentException("Price must be positive"); }
        if (stock < 0) { throw new IllegalArgumentException("Stock cannot be negative"); }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }              
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setPrice(double price) { 
        if (price <=0) { throw new IllegalArgumentException("Price must be positive"); }
        this.price = price; 
    }
    public void setStock(int stock) { 
        if (stock < 0) { throw new IllegalArgumentException("Stock cannot be negative"); }
        this.stock = stock; 
    }
    
    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("Not enough stock for product: " + name);
        }
    }

    @Override
    public String toString() {
        return id + " | " + name + " | Price: " + price + "€ | Stock: " + stock;
    }
}