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

        int userIndex = InputReader.getInt() - 1;
        int lastIndex = libraryManager.AllSongs().size() - 1;
        
        // Check the user supplied index is between the min/max index.
        if (userIndex >= 0 && userIndex <= lastIndex) {

            SongInfo selectesSong = libraryManager.AllSongs().get(userIndex);

            print("Selected song: " + selectesSong.getSongName());
            print("Are you sure wish to delete this song? Y/N");

            String response = InputReader.getString();

            if (response.equalsIgnoreCase("y")) {

                libraryManager.removeSongAtIndex(userIndex);
                returnToMainMenu("Song deleted!");
            } else {
                returnToMainMenu();
            }

        } else {
            returnToMainMenu("Invalid input!");
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
