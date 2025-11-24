import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CRMGUI {
    private CRMSystem crm;
    private JFrame frame;

    public CRMGUI(CRMSystem crm) {
        this.crm = crm;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Main window setup
        frame = new JFrame("Mini CRM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        // Top panel with action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 6, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addCustomerBtn = new JButton("Add Customer");
        JButton addProductBtn = new JButton("Add Product");
        JButton createOrderBtn = new JButton("Create Order");
        JButton showCustomersBtn = new JButton("Show Customers");
        JButton showProductsBtn = new JButton("Show Products");
        JButton showOrdersBtn = new JButton("Show Orders");

        // Apply consistent styling to all buttons
        Font btnFont = new Font("Arial", Font.BOLD, 14);
        Color btnColor = new Color(60, 120, 180);
        Color textColor = Color.WHITE;
        for (JButton btn : new JButton[]{addCustomerBtn, addProductBtn, createOrderBtn,
                showCustomersBtn, showProductsBtn, showOrdersBtn}) {
            btn.setFont(btnFont);
            btn.setBackground(btnColor);
            btn.setForeground(textColor);
        }

        // Add buttons to the top panel
        buttonPanel.add(addCustomerBtn);
        buttonPanel.add(addProductBtn);
        buttonPanel.add(createOrderBtn);
        buttonPanel.add(showCustomersBtn);
        buttonPanel.add(showProductsBtn);
        buttonPanel.add(showOrdersBtn);

        frame.add(buttonPanel, BorderLayout.NORTH);

        // Table area (center of layout)
        JPanel tablePanel = new JPanel(new BorderLayout());
        frame.add(tablePanel, BorderLayout.CENTER);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Button: Add new customer via input dialogs
        addCustomerBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Customer Name:");
            String email = JOptionPane.showInputDialog(frame, "Customer Email:");
            String phone = JOptionPane.showInputDialog(frame, "Customer Phone:");

            // Basic validation: user did not cancel
            if (name != null && email != null && phone != null) {
                try {
                    // Auto-generate incremental ID
                    int id = crm.getCustomers().size() + 1;
                    crm.addCustomer(new Customer(id, name, email, phone));
                    JOptionPane.showMessageDialog(frame, "Customer added!");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        // Button: Add new product
        addProductBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Product Name:");
            String priceStr = JOptionPane.showInputDialog(frame, "Product Price:");
            String stockStr = JOptionPane.showInputDialog(frame, "Product Stock:");

            if (name != null && priceStr != null && stockStr != null) {
                try {
                    double price = Double.parseDouble(priceStr);
                    int stock = Integer.parseInt(stockStr);

                    int id = crm.getProducts().size() + 1;
                    crm.addProduct(new Product(id, name, price, stock));

                    JOptionPane.showMessageDialog(frame, "Product added!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        // Button: Create a new order (loops to add multiple items)
        createOrderBtn.addActionListener(e -> {
            String customerIdStr = JOptionPane.showInputDialog(frame, "Customer ID:");

            if (customerIdStr != null) {
                try {
                    int cid = Integer.parseInt(customerIdStr);
                    Customer customer = crm.findCustomerById(cid);

                    if (customer == null) {
                        JOptionPane.showMessageDialog(frame, "Customer not found!");
                        return;
                    }

                    // Order starts empty; products added in loop
                    Order order = new Order(customer);

                    while (true) {
                        // Ask for product ID (0 = finish)
                        String productIdStr = JOptionPane.showInputDialog(frame, "Product ID (0 to finish):");
                        if (productIdStr == null) break;

                        int pid = Integer.parseInt(productIdStr);
                        if (pid == 0) break;

                        Product product = crm.findProductById(pid);
                        if (product == null) {
                            JOptionPane.showMessageDialog(frame, "Product not found!");
                            continue;
                        }

                        // Ask for quantity per product
                        String qtyStr = JOptionPane.showInputDialog(frame, "Quantity:");
                        if (qtyStr == null) continue;

                        int qty = Integer.parseInt(qtyStr);

                        try {
                            order.addProduct(product, qty);
                            JOptionPane.showMessageDialog(frame, "Product added to order!");
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                        }
                    }

                    // Save order if it has at least one item
                    if (!order.getItems().isEmpty()) {
                        crm.addOrder(order);
                        JOptionPane.showMessageDialog(frame, "Order created!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            }
        });

        // Display actions → update table contents
        showCustomersBtn.addActionListener(e -> displayCustomers(table));
        showProductsBtn.addActionListener(e -> displayProducts(table));
        showOrdersBtn.addActionListener(e -> displayOrders(table));

        frame.setVisible(true);
    }

    // Populate table with customer list
    private void displayCustomers(JTable table) {
        List<Customer> customers = crm.getCustomers();
        String[] columns = {"ID", "Name", "Email", "Phone"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Customer c : customers) {
            model.addRow(new Object[]{c.getId(), c.getName(), c.getEmail(), c.getPhone()});
        }
        table.setModel(model);
    }

    // Populate table with product list
    private void displayProducts(JTable table) {
        List<Product> products = crm.getProducts();
        String[] columns = {"ID", "Name", "Price", "Stock"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Product p : products) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getStock()});
        }
        table.setModel(model);
    }

    // Populate table with order list (each product becomes a separate row)
    private void displayOrders(JTable table) {
        List<Order> orders = crm.getOrders();
        String[] columns = {"Customer", "Product", "Quantity", "Total"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Order o : orders) {
            for (OrderItem item : o.getItems()) {
                model.addRow(new Object[]{
                        o.getCustomer().getName(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        String.format("%.2f€", item.getTotal())
                });
            }
        }
        table.setModel(model);
    }

    public static void main(String[] args) {
        CRMSystem crm = new CRMSystem();
        SwingUtilities.invokeLater(() -> new CRMGUI(crm));
    }
}