package mod_1_lab;

public class personType {
	// Private static variable that holds the single instance of PersonType
    private static personType instance = null;
    
	//declare private variable for the name
    private String firstName;
    private String lastName;
    private String middleName;
    
 // Public static method to provide access to the single instance
    public static personType getInstance() {
        if (instance == null) {
            instance = new personType();
        }
        return instance;
    }
    
    //constructor to initialize the name components
    public personType(String firstName, String lastName, String middleName) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.middleName = middleName;
    }
    
    //Default constructor
    public personType() {
    	this.firstName = "";
    	this.lastName = "";
    	this.middleName = "";
    }
    
 // Method to set the first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Method to set the last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Method to set the middle name
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // Method to get the first name
    public String getFirstName() {
        return firstName;
    }

    // Method to get the last name
    public String getLastName() {
        return lastName;
    }

    // Method to get the middle name
    public String getMiddleName() {
        return middleName;
    }

    // Method to return the full name
    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
    
    //Method to see if all 3 names are set
    public boolean isNameComplete() {
    	if ((firstName.length() > 0) && (middleName.length() > 0) && (lastName.length() > 0)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
