package Interaction;

import java.util.ArrayList;
import java.util.List;

import BaseClasses.BaseView;
import Models.SongInfo;

public class LibraryView extends BaseView{    

    private LibraryManager libraryManager;
    private ConsoleInteraction parentInteraction;

    public LibraryView(LibraryManager libraryManager, ConsoleInteraction parentInteraction) {
        super();
        this.libraryManager = libraryManager;
        this.parentInteraction = parentInteraction;
    }

    public void printSongs() {
       printSongs(libraryManager.AllSongs());
    }

	public void libraryViewMainMenu() {
        clearConsole();

        print("Press 1 to search the library.");
        print("Press 2 to show a list of songs above a given play count.");
        print("Press 3 to return to the main menu.");

        String response = InputReader.getString();

        switch (response) {
            case "1":
                printSearchMenu();
                break;

            case "2":
                printPlayCountMenu();
            break;

            case "3":
                parentInteraction.returnToMainMenu();
            break;
        
            default:
                parentInteraction.returnToMainMenu("Invalid response!");
                break;
        }
	}

    private void printPlayCountMenu() {

        clearConsole();

        print("Enter play count number to show a list of songs that have that many sounds or more:");

        int desiredPlayCount = InputReader.getInt();

        List<SongInfo> filteredSongs = new ArrayList<SongInfo>();

        for (SongInfo song : libraryManager.AllSongs()) {           

            if (song.getPlayCount() > desiredPlayCount) 
            {
                filteredSongs.add(song);
            }
        }

        if (filteredSongs.size() > 0) {
            printSongs(filteredSongs);
        }
        else{
            print("No songs found");
        }

        returnToMainMenu();
    }

    private void printSearchMenu(){

        clearConsole();
        
        print("Enter search criteria to find songs or artists that match:");

        String critera = InputReader.getString();

        List<SongInfo> filteredSongs = new ArrayList<SongInfo>();

        for (SongInfo song : libraryManager.AllSongs()) {           

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
