/*Class for interacting with the MySQL back end*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	//Connection credentials
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Orders";
	private static final String USER = "timmasta";
	private static final String PASSWORD = "Chewbacca82!";

	private Connection connection = null;

	//try a connection, log success or failure
	public DatabaseHandler() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			AppLog.getLogger().info("Connection to database established.");
		} catch (SQLException e) {
			AppLog.getLogger().info("Error connecting to the database.");
			e.printStackTrace();
		}
	}

	public void insertCustomer(String name, String email, String phone) {
		String query = "INSERT INTO Customers (customerName, email, phone) VALUES (?, ?, ?)";
		try (PreparedStatement prepStat = connection.prepareStatement(query)) {
			prepStat.setString(1, name);
			prepStat.setString(2, email);
			prepStat.setString(3, phone);
			prepStat.executeUpdate();			
			AppLog.getLogger().info("Customer inserted successfully.");
		} catch (SQLException e) {
			AppLog.getLogger().info("Error inserting customer.");
			e.printStackTrace();
		}
	}

	public void insertProduct(String name, String description, double price, int stockQuantity) {
		String query = "INSERT INTO Products (productName, description, price, stockQuantity) VALUES (?, ?, ?, ?)";
		try (PreparedStatement prepStat = connection.prepareStatement(query)) {
			prepStat.setString(1, name);
			prepStat.setString(2, description);
			prepStat.setDouble(3, price);
			prepStat.setInt(4, stockQuantity);
			prepStat.executeUpdate();
			AppLog.getLogger().info("Product inserted successfully.");
		} catch (SQLException e) {
			AppLog.getLogger().info("Error inserting product.");
			e.printStackTrace();
		}
	}

	public void insertOrder(String dateEntered, String dateModified, String orderStatus, int customerID,
			int quantity,double price, int productID) {
		String query = "INSERT INTO Orders (dateEntered, dateLastModified, orderStatus, customerID, quantity, price, productID) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement prepStat = connection.prepareStatement(query)) {
			prepStat.setString(1, dateEntered);
			prepStat.setString(2, dateEntered);//new order so copy order date
			prepStat.setString(3, orderStatus);
			prepStat.setInt(4, customerID);
			prepStat.setInt(5, quantity);
			prepStat.setDouble(6, price);
			prepStat.setInt(7, productID);
			prepStat.executeUpdate();
			AppLog.getLogger().info("Order inserted successfully.");
		} catch (SQLException e) {
			AppLog.getLogger().info("Error inserting order.");
			e.printStackTrace();
		}
	}
	
	public void updateOrderStatus(int orderID, String newStatus) {
	    String query = "UPDATE Orders SET orderStatus = ? WHERE orderNumber = ?";
	    try (PreparedStatement prepStat = connection.prepareStatement(query)) {
	        prepStat.setString(1, newStatus);
	        prepStat.setInt(2, orderID);
	        prepStat.executeUpdate();
	        AppLog.getLogger().info("Order status updated successfully for orderNumber: " + orderID);
	    } catch (SQLException e) {
	        AppLog.getLogger().info("Error updating order status for orderNumber: " + orderID);
	        e.printStackTrace();
	    }
	}
	
	public void searchOrderByID(int orderID) {
	    String query = "SELECT * FROM Orders WHERE orderNumber = ?";
	    try (PreparedStatement prepStat = connection.prepareStatement(query)) {
	        prepStat.setInt(1, orderID);
	        try (ResultSet rs = prepStat.executeQuery()) {
	            if (rs.next()) {
	                String dateEntered = rs.getString("dateEntered");
	                String dateLastModified = rs.getString("dateLastModified");
	                String orderStatus = rs.getString("orderStatus");
	                int customerID = rs.getInt("customerID");
	                int quantity = rs.getInt("quantity");
	                double price = rs.getDouble("price");
	                int productID = rs.getInt("productID");

	                // Log or print the details (replace this with UI pop-up by returning a structure of values)
	                AppLog.getLogger().info("Order ID: " + orderID + ", Date Entered: " + dateEntered + 
	                    ", Status: " + orderStatus + ", Customer ID: " + customerID + ", Quantity: " + quantity +
	                    ", Price: " + price + ", Product ID: " + productID);
	            } else {
	                AppLog.getLogger().info("No order found with ID: " + orderID);
	            }
	        }
	    } catch (SQLException e) {
	        AppLog.getLogger().info("Error retrieving order details for orderNumber: " + orderID);
	        e.printStackTrace();
	    }
	}
	
	public void generateReport(String startDate, String endDate) {
	    String query = "SELECT * FROM Orders WHERE dateEntered BETWEEN ? AND ?";
	    try (PreparedStatement prepStat = connection.prepareStatement(query)) {
	        prepStat.setString(1, startDate);
	        prepStat.setString(2, endDate);
	        
	        try (ResultSet rs = prepStat.executeQuery()) {
	            while (rs.next()) {
	                int orderNumber = rs.getInt("orderNumber");
	                String dateEntered = rs.getString("dateEntered");
	                String orderStatus = rs.getString("orderStatus");
	                int customerID = rs.getInt("customerID");
	                int quantity = rs.getInt("quantity");
	                double price = rs.getDouble("price");
	                int productID = rs.getInt("productID");

	                // Log or print the details (you can replace this with UI pop-up)
	                AppLog.getLogger().info("Order Number: " + orderNumber + ", Date Entered: " + dateEntered +
	                    ", Status: " + orderStatus + ", Customer ID: " + customerID + ", Quantity: " + quantity + 
	                    ", Price: " + price + ", Product ID: " + productID);
	            }
	        }
	    } catch (SQLException e) {
	        AppLog.getLogger().info("Error generating report for date range: " + startDate + " to " + endDate);
	        e.printStackTrace();
	    }
	}


	public void getCustomers() {
		String query = "SELECT * FROM Customers";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("customerID");
				String name = rs.getString("customerName");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				AppLog.getLogger().info("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone);
			}
		} catch (SQLException e) {
			AppLog.getLogger().info("Error retrieving customers.");
			e.printStackTrace();
		}
	}

	public void updateCustomerEmail(int customerID, String newEmail) {
		String query = "UPDATE Customers SET email = ? WHERE customerID = ?";
		try (PreparedStatement prepStat = connection.prepareStatement(query)) {
			prepStat.setString(1, newEmail);
			prepStat.setInt(2, customerID);
			prepStat.executeUpdate();
			AppLog.getLogger().info("Customer email updated successfully.");
		} catch (SQLException e) {
			AppLog.getLogger().info("Error updating customer email.");
			e.printStackTrace();
		}
	}

	public void deleteCustomer(int customerID) {
		String query = "DELETE FROM Customers WHERE customerID = ?";
		try (PreparedStatement prepStat = connection.prepareStatement(query)) {
			prepStat.setInt(1, customerID);
			prepStat.executeUpdate();
			AppLog.getLogger().info("Customer deleted successfully.");
		} catch (SQLException e) {
			AppLog.getLogger().info("Error deleting customer.");
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Add more verbose error messages
			e.printStackTrace();
		}
	}

}
