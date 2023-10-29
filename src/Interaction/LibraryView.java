package Interaction;

import java.util.ArrayList;
import java.util.List;

import BaseClasses.BaseView;
import Models.SongInfo;

public class LibraryView extends BaseView{    

    private LibraryManager libraryManager;

    public LibraryView(LibraryManager libraryManager) {
        super();
        this.libraryManager = libraryManager;
    }

    public void printSongs() {
       printSongs(libraryManager.AllSongs());
    }

	public void libraryViewMainMenu() {
        clearConsole();

        print("Press 1 to search the library.");
        print("Press 2 to search for a song.");

        String response = InputReader.getString();

        switch (response) {
            case "1":
                printSearchMenu();
                break;
        
            default:
                break;
        }
	}

    public void printSearchMenu(){

        clearConsole();
        
        print("Enter search criteria to find songs or artists that match:");

        String critera = InputReader.getString();

        List<SongInfo> filteredSongs = new ArrayList<SongInfo>();

        for (SongInfo song : libraryManager.AllSongs()) {           

            if (song.getSongName().toLowerCase().equals(critera.toLowerCase()) 
            || song.getArtistName().toLowerCase().equals(critera.toLowerCase())) {
                filteredSongs.add(song);
            }
        }

        if (filteredSongs.size() > 1) {
            printSongs(filteredSongs);
        }
        else{
            print("Nothing found!");
        }
    }



}
