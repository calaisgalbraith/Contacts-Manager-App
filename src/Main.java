import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Input input = new Input();

    //method to display menu & get user Input
    public static void displayMenu() throws IOException {

        //print menu
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.");

        //get user input
        int userChoice = input.getInt("Enter an option (1, 2, 3, 4, or 5)", 1, 5);

        //execute method based on user choice
        switch (userChoice){
            case 1: viewAllContacts();
                break;
            case 2: addNewContact();
                break;
            case 3: searchForContact();
                break;
            case 4: deleteExistingContact();
                break;
            default:
                System.out.println("Ending Application. Goodbye!");
                break;
        }
    }

    //method to view all contacts
    public static void viewAllContacts() throws IOException {
        //get contacts
        List<String> contacts = getContacts();

        //print out information
        System.out.println("Name | Phone number");
        System.out.println("---------------");

        for (String contact: contacts) {
            System.out.println(contact);
        }

        //see if user wants to continue
        if(input.getAnswer("Return to main menu? (y/n)")){
            displayMenu();
        }
        else{
            System.out.println("Ending Application. Goodbye!");
        }
    }

    //TODO method to add new contact
    public static void addNewContact() throws IOException {
        //get contacts
        List<String> contacts = getContacts();

        //get new contact information
        String firstName = input.getResponse("Enter first name: ");
        String lastName = input.getResponse("Enter last name: ");
        String phoneNumber = input.getResponse("Enter phone number: ");

        //TODO Validate phoneNumber length

        //Check if User already exists
        boolean contactAlreadyExists = false;

        for(String contact : contacts){
            //if contact already exists set contactAlreadyExists to true
            if (contact.contains(firstName + " " + lastName)) {
                contactAlreadyExists = true;
                break;
            }
        }

        //if contact does not already exist, create and add new contact
        if(!contactAlreadyExists){
            Contact newContact = new Contact(firstName, lastName, phoneNumber);
            contacts.add(newContact.getFirstName() + " " + newContact.getLastName() + " | " + newContact.getPhoneNumber());

            //update contact file to include new contact
            Files.write(Paths.get("data", "contacts.txt"), contacts);
        }

        //else check if user wants to override contact
        else{
            if(input.getAnswer(firstName + " " + lastName + " already exists as a contact. Do you want to want to overwrite it? (y/n)")){
                List<String> newContacts = new ArrayList<>(); //placeholder for new contact list

                //loop through existing contacts and add all except contact to override
                for(String contact : contacts){
                    if (!contact.contains(firstName + " " + lastName)) {
                        newContacts.add(contact);
                    }
                }

                //create and add new contact
                Contact newContact = new Contact(firstName, lastName, phoneNumber);
                newContacts.add(newContact.getFirstName() + " " + newContact.getLastName() + " | " + newContact.getPhoneNumber());

                //update contact file to include new contact
                Files.write(Paths.get("data", "contacts.txt"), newContacts);
            }
        }


        //check if want to add another contact, return to main menu, or exit
        if(input.getAnswer("Create another contact? (y/n)")){
            addNewContact();
        }
        else if(input.getAnswer("Return to main menu? (y/n)")){
            displayMenu();
        }
        else{
            System.out.println("Ending Application. Goodbye!");
        }

    }

    //method to search for contact by name
    public static void searchForContact() throws IOException {
        //get contacts
        List<String> contacts = getContacts();

        //get contact to search
        String searchContact = input.getResponse("Enter contact to search: ");

        //go through all lines in contacts and if contains searchContact, print it out
        for(String contact : contacts){
            if(contact.contains(searchContact)){
                System.out.println(contact);
            }
        }

        //see if want to search for another contact
        if(input.getAnswer("Search for another contact? (y/n)")){
            searchForContact();
        }
        else if(input.getAnswer("Return to main menu? (y/n)")){
            displayMenu();
        }
        else{
            System.out.println("Ending Application. Goodbye!");
        }
    }

    //TODO method to delete existing contact
    public static void deleteExistingContact() throws IOException {
        //get contacts
        List<String> contacts = getContacts();

        //get name to delete
        String deleteContact = input.getResponse("Enter name of contact to delete: ");

        //TODO Check if contact is present in list, if not print error message
        //TODO if multiple entries found (i.e John --> john smith, john doe) ask to clarify which one

        //confirm want to delete contact
        if(input.getAnswer("Are you sure you want to delete " + deleteContact + "? (y/n)")){

            List<String> updatedContacts = new ArrayList<>();

            for(String contact: contacts){
                //if contact is contact to delete, do not add it to updated contacts list
                if(!contact.contains(deleteContact)){
                    updatedContacts.add(contact);
                }
            }
            //update contact data file to updatedContacts w/ deleted contact
            System.out.println("Deletion Successful! ");
            Files.write(Paths.get("data", "contacts.txt"), updatedContacts);
        }

        //check if want to have another contact to delete, return to main menu, or exit
        if(input.getAnswer("Do you have another contact to delete? (y/n)")){
            deleteExistingContact();
        }
        else if(input.getAnswer("Do you want to return to the main menu? (y/n)")){
            displayMenu();
        }
        else{
            System.out.println("Ending Application. Goodbye!");
        }
    }

    //method to get list of all current contacts
    public static List<String> getContacts() throws IOException {
        Path contactsPath = Paths.get("data", "contacts.txt");
        return Files.readAllLines(contactsPath);
    }

    //TODO BONUS

    //method to create file if not exist
    public static void confirmDirectory() throws IOException {
        String directory = "data";
        String filename = "info.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }
    }

    public static void main(String[] args) throws IOException {
        confirmDirectory();
        displayMenu();
    }
}
