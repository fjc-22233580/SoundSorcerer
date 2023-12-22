package Interaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import BaseClasses.BaseView;
import Models.SongInfo;

/**
 * This class represents a view for interacting with the song library - mainly for list operations.
 * It provides methods for printing songs, displaying the this views specific main menu, sorting the library, searching for songs,
 * and returning to the main menu.
 */
public class LibraryView extends BaseView{    

    // Hold a reference to the library manager so we can access the library
    private LibraryManager libraryManager;

    // Hold a reference to the main menu interaction so we can return to it
    private ConsoleInteraction parentInteraction;

    public LibraryView(LibraryManager libraryManager, ConsoleInteraction parentInteraction) {
        super();
        this.libraryManager = libraryManager;
        this.parentInteraction = parentInteraction;
    }

	public void libraryViewMainMenu() {
        clearConsole();
        printTitleBar();

        print("Press 1 to search the library.");
        print("Press 2 to show a list of songs above a given play count.");
        print("Press 3 to show library sorting menu.");
        print("Press 4 to return to the main menu.");

        String response = InputReader.getString();

        switch (response) {
            case "1":
                printSearchMenu();
                break;

            case "2":
                printPlayCountMenu();
                break;

            case "3":
                printLibrarySortingMenu();
                break;

            case "4":
                parentInteraction.returnToMainMenu();
                break;

            default:
                parentInteraction.returnToMainMenu("Invalid response!");
                break;
        }
    }

    private void printLibrarySortingMenu(){

        clearConsole(); 
        printTitleBar();

        print("Press 1 to sort alphabetically by song name.");
        print("Press 2 to sort alphabetically by artist name.");
        print("Press 3 to sort alphabetically by artist name.");
        
        String responseString = InputReader.getString();

        switch (responseString) {
            case "1":
                Collections.sort(libraryManager.getAllSongs(), Comparator.comparing(SongInfo::getArtistName).reversed());
                printSongs(libraryManager.getAllSongs());
                break;

            case "2":
                Collections.sort(libraryManager.getAllSongs(), Comparator.comparing(SongInfo::getSongName).reversed());
                printSongs(libraryManager.getAllSongs());
                break;

            case "3":
                Collections.sort(libraryManager.getAllSongs(), Comparator.comparing(SongInfo::getPlayCount));
                printSongs(libraryManager.getAllSongs());
                break;
        
            default:
                print("Invalid response!");
                break;
        }

        returnToMainMenu();

    }

    private void printPlayCountMenu() {

        // Clear console and print title bar
        clearConsole();
        printTitleBar();
        print("Enter play count number to show a list of songs that have that many plays or more:");
        
        // Get and validate user input
        Integer desiredPlayCount = InputReader.getInt();
        if(desiredPlayCount != null) {

            List<SongInfo> filteredSongs = new ArrayList<SongInfo>();    
            for (SongInfo song : libraryManager.getAllSongs()) {           
    
                if (song.getPlayCount() > desiredPlayCount) 
                {
                    filteredSongs.add(song);
                }
            }
    
            if (filteredSongs.size() > 0) {
                printSongs(filteredSongs);
            }
            else { print("No songs found"); }

        } else { print("Invalid input!"); } // User entered an invalid input

        returnToMainMenu();
    }

    private void printSearchMenu(){

        clearConsole();
        printTitleBar();
        
        print("Enter search criteria to find songs or artists that match:");

        String critera = InputReader.getString();

        List<SongInfo> filteredSongs = new ArrayList<SongInfo>();

        for (SongInfo song : libraryManager.getAllSongs()) {

            if (song.getSongName().toLowerCase().contains(critera.toLowerCase())
                    || song.getArtistName().toLowerCase().contains(critera.toLowerCase())) {
                filteredSongs.add(song);
            }
        }

        if (filteredSongs.size() > 0) {
            printSongs(filteredSongs);
        }
        else{
            print("No songs found!");
        }

        returnToMainMenu();
    }

    private void returnToMainMenu() {
        print("Press enter to return to the main menu...");

        // Hold console for input
        InputReader.getString();

        libraryViewMainMenu();
    }
}
