import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double unitPrice;

    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}

class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " - Quantity: " + quantity + " - Total: $" + getTotal();
    }
}

public class InvoiceFrame extends JFrame {

    private List<LineItem> lineItems;
    private JTextField productNameField, unitPriceField, quantityField;
    private JTextArea invoiceTextArea;
    private double totalAmountDue;


    public InvoiceFrame() {
        // Initialize data structures
        lineItems = new ArrayList<>();
        totalAmountDue = 0;

        // Set up the JFrame
        setTitle("Invoice Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel unitPriceLabel = new JLabel("Unit Price:");
        JLabel quantityLabel = new JLabel("Quantity:");

        productNameField = new JTextField(15);
        unitPriceField = new JTextField(7);
        quantityField = new JTextField(5);

        JButton addButton = new JButton("Add Line Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLineItem();
            }
        });

        invoiceTextArea = new JTextArea();
        invoiceTextArea.setEditable(false);

        // Set up layout
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameField);
        inputPanel.add(unitPriceLabel);
        inputPanel.add(unitPriceField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        JScrollPane scrollPane = new JScrollPane(invoiceTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Display the JFrame
        setVisible(true);
    }

    private void addLineItem() {
        String productName = productNameField.getText();
        double unitPrice = Double.parseDouble(unitPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(productName, unitPrice);
        LineItem lineItem = new LineItem(product, quantity);
        lineItems.add(lineItem);

        totalAmountDue += lineItem.getTotal();

        updateInvoiceDisplay();
        clearInputFields();
    }

    private void updateInvoiceDisplay() {
        StringBuilder invoiceText = new StringBuilder("Invoice\n");
        for (LineItem lineItem : lineItems) {
            invoiceText.append(lineItem.toString()).append("\n");
        }
        invoiceText.append("\nTotal Amount Due: $").append(totalAmountDue);

        invoiceTextArea.setText(invoiceText.toString());
    }

    private void clearInputFields() {
        productNameField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
    }



}
