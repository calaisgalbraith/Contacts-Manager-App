import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        //get data
        Path contactsPath = Paths.get("data", "contacts.txt");

        //read lines in text
        List<String> contacts = Files.readAllLines(contactsPath);

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
    }

    //TODO method to search for contact by name
    public static void searchForContact() throws IOException {
        //get data
        Path contactsPath = Paths.get("data", "contacts.txt");

        //read lines in text
        List<String> contacts = Files.readAllLines(contactsPath);

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
    public static void deleteExistingContact(){

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
