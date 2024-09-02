import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserEntry {
	private JPanel mainPanel;
    private JTextField orderNumber;    
    private JSpinner dateEnteredSpinner;
    private JSpinner dateModifiedSpinner;
    private JTextField orderStatus;
    private JTextField customerID;
    private JSpinner quantity;
    private JTextField price;
    private JSpinner productID;
    private JButton submitButton;
    private JLabel orderNumberLabel;
    private JLabel dateEnteredLabel;
    private JLabel dateModifiedLabel;
    private JLabel orderStatusLabel;
    private JLabel customerIDLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JLabel productIDLabel;
    
    public UserEntry() {
    	//initialize the form components
    	
        
    	
        orderNumberLabel = new JLabel("Order Number:");
        orderNumber = new JTextField(20);

        dateEnteredLabel = new JLabel("Date Entered:");
        dateEnteredSpinner = new JSpinner(new SpinnerDateModel());
        
        dateModifiedLabel = new JLabel("Last Modified:");
        dateModifiedSpinner = new JSpinner(new SpinnerDateModel());

        orderStatusLabel = new JLabel("Status:");
        orderStatus = new JTextField(20);
        
        customerIDLabel = new JLabel("Customer ID:");
        customerID = new JTextField(20);

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
        gbc.gridy = 2;
        mainPanel.add(dateModifiedLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(dateModifiedSpinner, gbc);               
        
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
        System.out.println("Form submitted!");	
    }
    
    public JPanel getMainPanel() {
        return mainPanel;
    }
}




