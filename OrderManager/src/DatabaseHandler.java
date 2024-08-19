/*Class for interacting with the MySQL back end*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Orders";
	private static final String USER = "timmasta";
	private static final String PASSWORD = "Chewbacca82!";

	private Connection connection = null;

	public DatabaseHandler() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connection to database established.");
		} catch (SQLException e) {
			System.out.println("Error connecting to the database.");
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
			System.out.println("Customer inserted successfully.");
		} catch (SQLException e) {
			System.out.println("Error inserting customer.");
			e.printStackTrace();
		}
	}

	public void insertProduct(String name, String description, double price, int stockQuantity) {
		String query = "INSERT INTO Products (productName, description, price, stockQuantity) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, stockQuantity);
			pstmt.executeUpdate();
			System.out.println("Product inserted successfully.");
		} catch (SQLException e) {
			System.out.println("Error inserting product.");
			e.printStackTrace();
		}
	}

	public void insertOrder(String dateEntered, String dateLastModified, String orderStatus, int customerID,
			int quantity, double price, int productID) {
		String query = "INSERT INTO Orders (dateEntered, dateLastModified, orderStatus, customerID, quantity, price, productID) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, dateEntered);
			pstmt.setString(2, dateLastModified);
			pstmt.setString(3, orderStatus);
			pstmt.setInt(4, customerID);
			pstmt.setInt(5, quantity);
			pstmt.setDouble(6, price);
			pstmt.setInt(7, productID);
			pstmt.executeUpdate();
			System.out.println("Order inserted successfully.");
		} catch (SQLException e) {
			System.out.println("Error inserting order.");
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
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone);
			}
		} catch (SQLException e) {
			System.out.println("Error retrieving customers.");
			e.printStackTrace();
		}
	}

	public void updateCustomerEmail(int customerID, String newEmail) {
		String query = "UPDATE Customers SET email = ? WHERE customerID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, newEmail);
			pstmt.setInt(2, customerID);
			pstmt.executeUpdate();
			System.out.println("Customer email updated successfully.");
		} catch (SQLException e) {
			System.out.println("Error updating customer email.");
			e.printStackTrace();
		}
	}

	public void deleteCustomer(int customerID) {
		String query = "DELETE FROM Customers WHERE customerID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, customerID);
			pstmt.executeUpdate();
			System.out.println("Customer deleted successfully.");
		} catch (SQLException e) {
			System.out.println("Error deleting customer.");
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
