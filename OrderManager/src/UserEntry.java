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
    	mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));
    	
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

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleSubmit());
        
        mainPanel.add(orderNumber);
        mainPanel.add(dateEnteredSpinner);
        mainPanel.add(dateModifiedSpinner);
        mainPanel.add(orderStatus);
        mainPanel.add(customerID);
        mainPanel.add(quantity);
        mainPanel.add(price);
        mainPanel.add(productID);
        mainPanel.add(submitButton);
    	
    }
    
    private void handleSubmit() {
        System.out.println("Form submitted!");	
    }
    
    public JPanel getMainPanel() {
        return mainPanel;
    }
}




