public class Customer {
    private final int id;
    private String name;
    private String email;
    private String phone;

    // Constructor with validation
    public Customer(int id, String name, String email, String phone) {
        // Ensure name is not null or empty
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        // Basic email format check
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        // Ensure phone contains only digits
        if (phone == null || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone must contain only digits");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // Setters with validation
    public void setEmail(String email) { 
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email; 
    }

    public void setPhone(String phone) { 
        if (phone == null || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone must contain only digits");
        }
        this.phone = phone; 
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email + " | " + phone;
    }
}