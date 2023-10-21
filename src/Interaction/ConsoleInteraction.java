package Interaction;

import Models.SongInfo;

public class ConsoleInteraction {
    
    private LibraryManager libraryManager;

    public ConsoleInteraction(LibraryManager libraryManager) {
        super();
        this.libraryManager = libraryManager;

        mainMenu();

    }

    private void mainMenu(){

        print("Press 1 to display all songs.");
        print("Press 2 to add a new songs.");
        print("Press x to close.");

        String reponse = InputReader.getString();


        switch (reponse) {
            case "1":
                printLibrary();
                break;

            case "2":
                // TODO - Next logic
                break;

            case "x":
                System.exit(0);
                break;

            default:
                break;
        }
                
        mainMenu();
        
    }

    private void printLibrary(){

        int count = 1;

        for (SongInfo songInfo : libraryManager.AllSongs()) {
            print(count + ". " + songInfo.songName() + " " + songInfo.artistName());
            count++;
        }
    }

    private void print(String text){
        System.out.println(text);
    }
}
