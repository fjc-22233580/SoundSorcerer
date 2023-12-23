package Interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import BaseClasses.BaseView;
import Models.SongInfo;

/**
 * This class represents a view for interacting with the song library - mainly
 * for list operations.
 * It provides methods for printing songs, displaying the this views specific
 * main menu, sorting the library, searching for songs,
 * and returning to the main menu.
 */
public class LibraryView extends BaseView {

    // Hold a reference to the library manager so we can access the library
    private LibraryManager libraryManager;

    // Hold a reference to the main menu interaction so we can return to it
    private ConsoleInteraction parentInteraction;

    public LibraryView(LibraryManager libraryManager, ConsoleInteraction parentInteraction) {
        super();
        this.libraryManager = libraryManager;
        this.parentInteraction = parentInteraction;
    }

    /**
     * Prints the main menu for the library view.
     * Allows the user to choose different options such as searching the library,
     * showing a list of songs above a given play count, and accessing the library
     * sorting menu.
     * Also provides an option to return to the parents main menu.
     */
    public void libraryViewMainMenu() {

        // Clear console and print title bar
        clearConsole();
        printTitleBar();

        // Print menu options
        print("Press 1 to search the library.");
        print("Press 2 to show a list of songs above a given play count.");
        print("Press 3 to show library sorting menu.");
        print("Press 4 to return to the main menu.");

        // Get and process user input
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

    /**
     * Prints the library sorting menu and performs the corresponding sorting operation based on user input.
     */
    private void printLibrarySortingMenu() {

        // Clear console and print title bar
        clearConsole();
        printTitleBar();

        // Print menu options
        print("Press 1 to sort alphabetically by artist name.");
        print("Press 2 to sort alphabetically by song name.");
        print("Press 3 to sort by play count.");

        // Get and process user input
        String response = InputReader.getString();
        switch (response) {

            // Sort alphabetically by artist name
            case "1":
                Collections.sort(libraryManager.getAllSongs(), Comparator.comparing(SongInfo::getArtistName));
                printSongsInTableFormat(libraryManager.getAllSongs());
                break;

            // Sort alphabetically by song name
            case "2":
                Collections.sort(libraryManager.getAllSongs(), Comparator.comparing(SongInfo::getSongName));
                printSongsInTableFormat(libraryManager.getAllSongs());
                break;

            // Sort by play count
            case "3":
                Collections.sort(libraryManager.getAllSongs(), Comparator.comparing(SongInfo::getPlayCount));
                printSongsInTableFormat(libraryManager.getAllSongs());
                break;

            default:
                print("Invalid response!");
                break;
        }

        returnToSubMenu();
    }

    /**
     * Prints the play count menu and allows the user to enter a play count number.
     * Displays a list of songs that have a play count greater than than the entered value.
     */
    private void printPlayCountMenu() {

        List<SongInfo> filteredSongs = new ArrayList<SongInfo>();

        // Clear console and print title bar
        clearConsole();
        printTitleBar();

        // Print user prompt
        print("Enter play count number to show a list of songs that have that many plays or more:");

        // Get and validate user input
        Integer desiredPlayCount = InputReader.getInt();
        if (desiredPlayCount != null) {

            // Create a list of songs that have a play count greater than the entered value
            for (SongInfo song : libraryManager.getAllSongs()) {

                if (song.getPlayCount() > desiredPlayCount) {
                    filteredSongs.add(song);
                }
            }

            // Print the list of songs, if we found any
            if (filteredSongs.size() > 0) {
                printSongsInTableFormat(filteredSongs);

            } else { print("No songs found"); }

        } else {  print("Invalid input!"); } // User entered an invalid input

        returnToSubMenu();
    }

    /**
     * Prints the search menu and allows the user to search for songs or artists based on user input search string.
     */
    private void printSearchMenu() {

        List<SongInfo> filteredSongs = new ArrayList<SongInfo>();

        // Clear console and print title bar
        clearConsole();
        printTitleBar();

        // Print user prompt
        print("Enter search criteria to find songs or artists that match:");

        // Get and validate user input, then iterate through songs to find any matches
        String critera = InputReader.getString();
        for (SongInfo song : libraryManager.getAllSongs()) {

            if (song.getSongName().toLowerCase().contains(critera.toLowerCase())
                    || song.getArtistName().toLowerCase().contains(critera.toLowerCase())) {
                filteredSongs.add(song);
            }
        }
        
        // Print the list of songs, if we found any
        if (filteredSongs.size() > 0) {
            printSongsInTableFormat(filteredSongs);
        } else {
            print("No songs found!");
        }

        returnToSubMenu();
    }

    /**
     * Returns to the sub-menu after displaying a message and waiting for user input.
     */
    private void returnToSubMenu() {
        print("Press enter to return to the main menu...");

        // Hold console for input, then return to the sub menu
        InputReader.getString();
        libraryViewMainMenu();
    }
}
