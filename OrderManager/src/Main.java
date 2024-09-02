import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		AppLog.getLogger().info("Application Started. Attempting Database connection.");
		//create a database handler instance that establishes the SQL connection
		DatabaseHandler dbHandler = new DatabaseHandler();
		
		//add a random number for unique name for testing without a UI
		final Random RANDOM = new Random();
		int randomNumber = RANDOM.nextInt(1000) + 1;
		
		// Example: Insert a 'new' customer
		String name = "John Doe" + randomNumber;
        dbHandler.insertCustomer(name, "john" + randomNumber + ".doe@example.com", "555-9876");

        // Example: Insert a duplicate customer
        dbHandler.insertCustomer("John Doe", "john.doe@example.com", "555-9876");
        
     // Set up the Swing UI with error handling
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Order Entry Form");
                UserEntry userEntry = new UserEntry();
                frame.setContentPane(userEntry.getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                
                AppLog.getLogger().info("Order Entry form loaded");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred while setting up the UI: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                AppLog.getLogger().info("Error loading UI form.");
            }
        });

        // Example: Fetch and print customers
        dbHandler.getCustomers();

        dbHandler.closeConnection();

	}

}
