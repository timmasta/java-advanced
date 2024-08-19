
public class Main {

	public static void main(String[] args) {
		//create a database handler instance that establishes the SQL connection
		DatabaseHandler dbHandler = new DatabaseHandler();

        // Example: Insert a customer
        dbHandler.insertCustomer("John Doe", "john.doe@example.com", "555-9876");

        // Example: Fetch and print customers
        dbHandler.getCustomers();

        dbHandler.closeConnection();

	}

}
