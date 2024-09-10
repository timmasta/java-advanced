import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class UserOptions {
	private JPanel mainPanel;
	private JButton enterOrderButton;
	private JButton searchButton;
	private JButton modifyButton;
	private JButton reportButton;
	
	private DatabaseHandler dbHandler; // Add a DatabaseHandler instance variable
	
	public UserOptions(DatabaseHandler dbHandler) {
		this.dbHandler = dbHandler; // Initialize the dbHandler
		
		//instantiate the buttons
		enterOrderButton = new JButton("Enter Order");
		searchButton = new JButton("Search by Order #");
		modifyButton = new JButton("Modify Status");
		reportButton = new JButton("Generate Report");
		
		//create a uniformly sized button for all
		Dimension buttonSize = new Dimension(150, 40);
        enterOrderButton.setPreferredSize(buttonSize);
        searchButton.setPreferredSize(buttonSize);
        modifyButton.setPreferredSize(buttonSize);
        reportButton.setPreferredSize(buttonSize);
        
		//create a grid bag to keep the buttons nicely arranged
		mainPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // add padding around components
		
        gbc.gridx = 0;
        gbc.gridy = 0;
		mainPanel.add(enterOrderButton, gbc);
        enterOrderButton.addActionListener(e -> orderEntry());
        
        gbc.gridx = 0;
        gbc.gridy = 1;
		mainPanel.add(searchButton, gbc);
        searchButton.addActionListener(e -> searchOrders());
        
        gbc.gridx = 0;
        gbc.gridy = 2;
		mainPanel.add(modifyButton, gbc);
        modifyButton.addActionListener(e -> modifyOrder());
        
        gbc.gridx = 0;
        gbc.gridy = 3;
		mainPanel.add(reportButton, gbc);
        reportButton.addActionListener(e -> orderEntry());
	}
	
	private void orderEntry() {
    	try {
    		// Set up the Swing UI with error handling
            SwingUtilities.invokeLater(() -> {
                try {
                    JFrame frame = new JFrame("Enter an Order");
                    UserEntry userEntry = new UserEntry(dbHandler);
                    frame.setContentPane(userEntry.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 600); // Set width and height
                    frame.setMinimumSize(new Dimension(400, 300));
                    frame.pack();
                    frame.setVisible(true);
                    
                    AppLog.getLogger().info("Order entry form loaded");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while setting up the UI: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    AppLog.getLogger().info("Error loading UI form.");
                }
            });            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while submitting the order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            AppLog.getLogger().info("Error submitting order details.");
        }
    }
	
	private void searchOrders() {
    	try {
    		// Set up the Swing UI with error handling
            SwingUtilities.invokeLater(() -> {
                try {
                    JFrame frame = new JFrame("Search for an Order");
                    OrderSearch orderSearch = new OrderSearch(dbHandler);
                    frame.setContentPane(orderSearch.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 600); // Set width and height
                    frame.setMinimumSize(new Dimension(400, 300));
                    frame.pack();
                    frame.setVisible(true);
                    
                    AppLog.getLogger().info("Order search form loaded");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while setting up search form: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    AppLog.getLogger().info("Error loading UI form.");
                }
            });            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while submitting the order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            AppLog.getLogger().info("Error submitting order details.");
        }
    }
	
	private void modifyOrder() {
    	try {
    		// Set up the Swing UI with error handling
            SwingUtilities.invokeLater(() -> {
                try {
                    JFrame frame = new JFrame("Modify an Order");
                    OrderModify orderModify = new OrderModify(dbHandler);
                    frame.setContentPane(orderModify.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 600); // Set width and height
                    frame.setMinimumSize(new Dimension(400, 300));
                    frame.pack();
                    frame.setVisible(true);
                    
                    AppLog.getLogger().info("Modify order form loaded");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while setting up the modify order form: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    AppLog.getLogger().info("Error loading UI form.");
                }
            });            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while submitting the order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            AppLog.getLogger().info("Error submitting order details.");
        }
    }
	
    public JPanel getMainPanel() {
        return mainPanel;
    }
	
}
