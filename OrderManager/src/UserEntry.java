import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserEntry {
	private JPanel mainPanel;
    private JTextField orderNumber;    
    private JSpinner dateEnteredSpinner;
    private JSpinner dateModifiedSpinner;
    private JTextField orderStatus;
    private JSpinner customerID;
    private JSpinner quantity;
    private JTextField price;
    private JSpinner productID;
    private JButton submitButton;
    private JLabel orderNumberLabel;
    private JLabel dateEnteredLabel;    
    private JLabel orderStatusLabel;
    private JLabel customerIDLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JLabel productIDLabel;
    
    private DatabaseHandler dbHandler; // Add a DatabaseHandler instance variable
    
    public UserEntry(DatabaseHandler dbHandler) {
    	this.dbHandler = dbHandler; // Initialize the dbHandler
    	
    	
    	//initialize the form components    	           	
        orderNumberLabel = new JLabel("Order Number:");
        orderNumber = new JTextField(20);

        dateEnteredLabel = new JLabel("Date Entered:");
        dateEnteredSpinner = new JSpinner(new SpinnerDateModel());                

        orderStatusLabel = new JLabel("Status:");
        orderStatus = new JTextField(20);
        
        customerIDLabel = new JLabel("Customer ID:");
        customerID = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));

        quantityLabel = new JLabel("Quantity:");
        quantity = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));
        
        priceLabel = new JLabel("Price:");
        price = new JTextField(20);
        price.setEditable(false);//make this read only, prices are determined by the back end
        
        productIDLabel = new JLabel("Product ID:");
        productID = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));
            
        mainPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // add padding around components
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(orderNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(orderNumber, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(dateEnteredLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(dateEnteredSpinner, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(orderStatusLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(orderStatus, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(customerIDLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(customerID, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(quantityLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(quantity, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(priceLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(price, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(productIDLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(productID, gbc);
    	
        submitButton = new JButton("Enter Order");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2; // span across two columns
        mainPanel.add(submitButton, gbc);
        submitButton.addActionListener(e -> handleSubmit());
        
    	
    }
    
    private void handleSubmit() {
    	try {
            // Extract data from form fields
            //String orderNum = orderNumber.getText();
    		Date dateEntered = (Date) dateEnteredSpinner.getValue();            
            String status = orderStatus.getText();
            int custID = (Integer) customerID.getValue();
            int qty = (Integer) quantity.getValue();            
            int prodID = (Integer) productID.getValue();
            double pricePlaceholder = 9.99;
            
         // Format the dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDateEntered = dateFormat.format(dateEntered);
            

            // Use dbHandler to insert the order into the database
            dbHandler.insertOrder(formattedDateEntered, formattedDateEntered, status, custID, qty, pricePlaceholder, prodID);

            JOptionPane.showMessageDialog(null, "Order details added successfully!");
            AppLog.getLogger().info("Order details submitted to the database.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while submitting the order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            AppLog.getLogger().info("Error submitting order details.");
        }
    }    
    public JPanel getMainPanel() {
        return mainPanel;
    }
}




