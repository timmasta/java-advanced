import java.util.Random;
public class Main {

	public static void main(String[] args) {
		AppLog.getLogger().info("Application Started. Attempting Database connection.");
		//create a database handler instance that establishes the SQL connection
		DatabaseHandler dbHandler = new DatabaseHandler();
		
		//add a random number for unique name
		final Random RANDOM = new Random();
		int randomNumber = RANDOM.nextInt(1000) + 1;
		
		// Example: Insert a 'new' customer
		String name = "John Doe" + randomNumber;
        dbHandler.insertCustomer(name, "john" + randomNumber + ".doe@example.com", "555-9876");

        // Example: Insert a duplicate customer
        dbHandler.insertCustomer("John Doe", "john.doe@example.com", "555-9876");
        
     

        // Example: Fetch and print customers
        dbHandler.getCustomers();

        dbHandler.closeConnection();

	}

}
