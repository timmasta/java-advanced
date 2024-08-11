
package mod_1_lab;

import java.util.Scanner;

/**
 * Main loop to test the personType Class
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Get the single instance of PersonType
		personType person = personType.getInstance();
		boolean exitLoop = false;

		while (!exitLoop) {

			// Display options to the user and get user input
			displayOptions();

			String userInput = getInput("Enter the person's First Name:");
			if (userInput != null) {
				person.setFirstName(userInput);
			}

			userInput = getInput("Enter the person's Middle Name:");
			if (userInput != null) {
				person.setMiddleName(userInput);
			}

			userInput = getInput("Enter the person's Last Name:");
			if (userInput != null) {
				person.setLastName(userInput);
			}
			
			if (person.isNameComplete()) {
				exitLoop = true;
			}
			else {
				System.out.println("Please enter all 3 names.");
			}

		}
		// Set the names (TEST CODE)
		/*
		 * person.setFirstName("first"); person.setLastName("last");
		 * person.setMiddleName("middle");
		 */

		// Output the full name
		System.out.println("Full Name: " + person.getFullName());

		// Get the instance again to prove it is a 'singleton'
		personType anotherPersonReference = personType.getInstance();

		// Verify that the same instance is used
		System.out.println("Full Name from another reference: " + anotherPersonReference.getFullName());

	}

	// Function to display user options
	public static void displayOptions() {
		System.out.println("Welcome to the Singleton person creator application!");
		System.out.println("You will be prompted to enter a first, middle and last name.");
		System.out.println("When you are done the name will be displayed.");
		System.out.println("=============================================");
		System.out.println("");
	}

	// Function to get user choice
	public static int getUserChoice() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your choice: ");
		int userChoice = scanner.nextInt();
		return userChoice;
	}

	// Function to get user input for names
	public static String getInput(String prompt) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(prompt);
		String userChoice = scanner.nextLine();
		return userChoice;
	}

}
