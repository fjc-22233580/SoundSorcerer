package Interaction;
import BaseClasses.BaseView;
import Models.SongInfo;
import HelperMethods.HelperMethods;

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
     * 
     */
    private void printMainMenu(){

        // Clear the current window console.
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

    private void removeSong(){
        clearConsole();

        printTitleBar();
        
        libraryView.printSongs();
        print("Please enter the number of the song you wish to delete: ");
        print("... or press x to return to main menu. ");

        // Validate user response
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
                int lastItemIndex = libraryManager.AllSongs().size() - 1;

                // Check the user supplied index is between the min/max index.
                if (userIndex >= 0 && userIndex <= lastItemIndex) {

                    // Get the song at the selected index.
                    SongInfo selectesSong = libraryManager.AllSongs().get(userIndex);

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

                        // User enter something other than yes.
                        returnToMainMenu();
                    }
                                        
                } else { 

                    // User selected index is outside of the number of songs in our library.
                    returnToMainMenu("Argument out of range!"); 
                }
            } 
        }
    }   

    private void addNewSong(){

        boolean invalidArgs = false;

        clearConsole();

        printTitleBar();

        final String Flag = "add:";

        print("Enter " + Flag + " followed by the song title, artist and play count seperated by commas");
        print("or press enter x to return to the main menu");

        String response = InputReader.getString();

        if (response.contains(Flag)) {

            // Remove the flag from the beginning of the string
            String song = response.replace(Flag, "");

            // Split the string into each part, using a comme as the delimiter
            // Note: this also checks for empty strings.
            String[] items = song.split(",");

            if (items.length == 3) {

                String songTitle = items[0].trim();
                String artistName = items[1].trim();
                Integer playCount = HelperMethods.tryParseInt(items[2].trim());

                if (!HelperMethods.isStringNullOrEmpty(songTitle)
                        && !HelperMethods.isStringNullOrEmpty(artistName)
                        && playCount != null) {

                    libraryManager.addSong(songTitle, artistName, playCount);

                    print("New Song successfully added!");

                    returnToMainMenu();

                } else {
                    invalidArgs = true;
                }

            } else {
                invalidArgs = true;
            }

        } else if (response.equals("x")) {
            returnToMainMenu();
        } else {
            invalidArgs = true;
        }

        if (invalidArgs) {
            returnToMainMenu("Error: Invalid/missing arguments have been supplied.");
        }
    }

    private void printLibrary(){

        clearConsole();

        printTitleBar();

        libraryView.printSongs();
        returnToMainMenu();
    }

    public void returnToMainMenu(String extraMessage) {
        print(extraMessage);
        returnToMainMenu();
    }

    public void returnToMainMenu() {
        
        print("Press enter to return to the main menu...");

        // Hold console for input
        InputReader.getString();

        printMainMenu();
    }
}
