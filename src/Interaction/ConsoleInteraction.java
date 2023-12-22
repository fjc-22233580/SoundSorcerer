package Interaction;
import BaseClasses.BaseView;
import Models.SongInfo;
import HelperMethods.HelperMethods;

/**
 * This class represents the console interaction for the SoundSorcerer application.
 * It uses also uses common printing functionaility in the BaseView class and provides methods for displaying menus, adding and removing songs,
 * and interacting with the library manager.
 */
public class ConsoleInteraction extends BaseView {
    
    private LibraryManager libraryManager;
    private LibraryView libraryView;

    public ConsoleInteraction(LibraryManager libraryManager) {
        super();
        this.libraryManager = libraryManager;

        libraryView = new LibraryView(libraryManager, this);

        // Show the main menu, this is the fist menu to be displayed. 
        printMainMenu();
    }
    
    /**
     * Prints the main menu options and processes the user's response.
     */
    private void printMainMenu(){

        // Clear the current window console and print the title bar.
        clearConsole();
        printTitleBar();

        // Print the main menu.
        print("Press 1 to display all songs.");
        print("Press 2 to add a new song.");
        print("Press 3 to delete a song.");
        print("Press 4 to go to list sorting menu.");
        print("Press x to close.");

        // Get the users response and process.
        String response = InputReader.getString();
        switch (response) {
            
            case "1":
                printLibrary();
                break;

            case "2":
                addNewSong();
                break;
            
            case "3":
                removeSong();
                break;

            case "4":
                libraryView.libraryViewMainMenu();
                break;

            case "x":
                System.exit(0);
                break;

            default:
                returnToMainMenu("Invalid response!");
                break;
        }        
    }

    /**
     * Removes a song from the library based on user input - a number of the song.
     * If the input is valid (a number), then the selected song is deleted from the library (after confirmation)
     * If the input is invalid or out of range, an appropriate error message is displayed.
     * If the user chooses to return to the main menu, the method returns without deleting any song.
     */
    private void removeSong(){
        clearConsole();

        printTitleBar();
        
        // Print all songs in the library
        libraryView.printSongs();

        // Print instructions
        print("Please enter the number of the song you wish to delete: ");
        print("... or press x to return to main menu. ");

        // Get and validate user response
        String response = InputReader.getString();
        if (HelperMethods.isStringNullOrEmpty(response) || HelperMethods.containsAlphabet(response)) {
            
            // Check if user chosen to return to main menu
            if (response.equals("x")) {
                returnToMainMenu();
            }

            returnToMainMenu("Invalid input!");
        } else {

            // Check we can parse the response to an int
            Integer userIndex = HelperMethods.tryParseInt(response.trim());
            if (userIndex != null) {

                // Convert user index to zero-based index. 
                userIndex --;
                // Get index of last element
                int lastItemIndex = libraryManager.getAllSongs().size() - 1;

                // Check the user supplied index is between the min/max index.
                if (userIndex >= 0 && userIndex <= lastItemIndex) {

                    // Get the song at the selected index.
                    SongInfo selectesSong = libraryManager.getAllSongs().get(userIndex);

                    // Print selected song and deletion confirmation message
                    print("Selected song: " + selectesSong.getSongName());
                    print("Are you sure wish to delete this song? Y/N");

                    // Check user response
                    String deletionResponse = InputReader.getString();
                    if (deletionResponse.equalsIgnoreCase("y")) {

                        // Remove selected index
                        libraryManager.removeSongAtIndex(userIndex);
                        returnToMainMenu("Song deleted!");

                    } else {

                        // User entered something other than yes.
                        returnToMainMenu();
                    }
                                        
                } else { 

                    // User selected index is outside of the number of songs in our library.
                    returnToMainMenu("Argument out of range!"); 
                }
            } 
        }
    }   

    /**
     * Adds a new song to the library. 
     * The user is prompted to enter the song title, artist, and play count separated by commas.
     * If the input is valid, the song is added to the library with the user supplier details.
     */
    private void addNewSong(){
        
        final String Flag = "add:";

        // Flag to indicate if the user has supplied invalid arguments.
        boolean invalidArgs = false;

        // Clear the console and print the title bar.
        clearConsole();
        printTitleBar();

        // Print instructions
        print("Enter " + Flag + " followed by the song title, artist and play count seperated by commas");
        print("or press enter x to return to the main menu");

        // Get and validate user response
        String response = InputReader.getString();
        if (response.contains(Flag)) {

            // Remove the flag from the beginning of the string
            String song = response.replace(Flag, "");

            // Split the string into each part, using a comma as the delimiter,
            // then check we have the correct number of args.
            // Note: this also checks for empty strings.
            String[] items = song.split(",");
            if (items.length == 3) {

                // Get and process the args from our array of inputs.
                String songTitle = items[0].trim();
                String artistName = items[1].trim();
                Integer playCount = HelperMethods.tryParseInt(items[2].trim());

                // Validate the args, and add the song if all okay,
                // otherwise set the invalidArgs flag to true.
                if (HelperMethods.isStringNullOrEmpty(songTitle) == false
                        && HelperMethods.isStringNullOrEmpty(artistName) == false
                        && playCount != null) {
                    
                    libraryManager.createSong(songTitle, artistName, playCount);
                    print("New Song successfully added!");
                    returnToMainMenu();

                } else { invalidArgs = true; } // Invalid args supplied

            } else { invalidArgs = true; } // Invalid number of args supplied

        } else if (response.equalsIgnoreCase("x")) {

            // User chose to return to main menu
            returnToMainMenu();

        } else { invalidArgs = true; } // Invalid exit command supplied

        // Process invalid args: show a prompt and return to main menu
        if (invalidArgs) {
            returnToMainMenu("Error: Invalid/missing arguments have been supplied.");
        }
    }

    /**
     * Prints the library of songs to the console, then prints a prompt to return to the main menu.     * 
     */
    private void printLibrary(){

        clearConsole();

        printTitleBar();

        libraryView.printSongs();
        returnToMainMenu();
    }

    /**
     * Overloaded returnToMainMenu method which prints an additional message.
     * @param extraMessage the extra message to be printed
     */
    public void returnToMainMenu(String extraMessage) {
        print(extraMessage);
        returnToMainMenu();
    }

    /**
     * Prints a prompt to return to the main menu, then waits for the user to press enter.
     */
    public void returnToMainMenu() {
        
        print("Press enter to return to the main menu...");

        // Hold console for input
        InputReader.getString();
        printMainMenu();
    }
}
