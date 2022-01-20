public class Main {

    private static final Input input = new Input();

    //method to display menu & get user Input
    public static void displayMenu(){

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
            default: ;
        }

        System.out.println("Ending Application. Goodbye!");
    }

    //method to view all contacts
    public static void viewAllContacts(){

    }

    //method to add new contact
    public static void addNewContact(){

    }

    //method to search for contact by name
    public static void searchForContact(){

    }

    //method to delete existing contact
    public static void deleteExistingContact(){

    }

    //method to see if user wants to continue
    public static void keepGoing(){

    }


    public static void main(String[] args) {

    }
}
