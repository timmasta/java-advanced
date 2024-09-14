import java.awt.*;
import javax.swing.*;

public class OrderSearch {
	// form components
	private JPanel mainPanel;
	private JTextField orderNumber;
	private JLabel orderNumberLabel;
	private JButton submitButton;

	private DatabaseHandler dbHandler; // Add a DatabaseHandler instance variable

	public OrderSearch(DatabaseHandler dbHandler) {
		this.dbHandler = dbHandler; // Initialize the dbHandler

		orderNumberLabel = new JLabel("Order Number:");
		orderNumber = new JTextField(20);

		mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components

		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(orderNumberLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		mainPanel.add(orderNumber, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2; // span across two columns
		submitButton = new JButton("Search Orders");

		mainPanel.add(submitButton, gbc);
		submitButton.addActionListener(e -> submitSearch());
	}

	private void submitSearch() {
		try {
			// Extract data from form fields - add better error handling to this in case the
			// input is not an integer
			String stringOrderNum = orderNumber.getText();
			int orderNum = Integer.parseInt(stringOrderNum);

			// Use dbHandler to insert the order into the database
			dbHandler.searchOrderByID(orderNum);

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
