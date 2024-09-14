import java.awt.*;
import java.util.Date;

import javax.swing.*;

public class GenerateReport {
    //form components
	private JPanel mainPanel;
	private JSpinner startDate;
	private JLabel startDateLabel;
	private JSpinner endDate;
	private JLabel endDateLabel;
	private JLabel instructionsLabel;
	private JButton submitButton;
	
	private DatabaseHandler dbHandler; // Add a DatabaseHandler instance variable
	
	public GenerateReport(DatabaseHandler dbHandler) {
		this.dbHandler = dbHandler; // Initialize the dbHandler
		
		instructionsLabel = new JLabel("Enter the date range and the Orders Database will be searched:");
		
		startDateLabel = new JLabel("Start Date:");
        startDate = new JSpinner(new SpinnerDateModel());
        
        endDateLabel = new JLabel("End Date:");
        endDate = new JSpinner(new SpinnerDateModel());             
        
        mainPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // add padding around components
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;//span two columns for instructions
        mainPanel.add(startDateLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;//reset to 2 separate columns
        mainPanel.add(startDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(startDate, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(endDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(endDate, gbc);
        
        submitButton = new JButton("Search Orders");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // span across two columns
        mainPanel.add(submitButton, gbc);
        submitButton.addActionListener(e -> handleSubmit());
	}
	
        private void handleSubmit() {
    		try {
    			// Extract data from form fields - add better error handling to this in case the
    			// input is not an integer
    			Date date1 = (Date) startDate.getValue();
    			Date date2 = (Date) endDate.getValue();

    			// Use dbHandler to insert the order into the database
    			dbHandler.generateReport(date1, date2);

    			JOptionPane.showMessageDialog(null, "Order search conducted! Check log for details");
    			AppLog.getLogger().info("Order search submitted to the database.");

    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "An error occurred while submitting the order: " + e.getMessage(),
    					"Error", JOptionPane.ERROR_MESSAGE);
    			AppLog.getLogger().info("Error submitting order details.");
    		}
    	}

    	public JPanel getMainPanel() {
    		return mainPanel;
    	}
	

}