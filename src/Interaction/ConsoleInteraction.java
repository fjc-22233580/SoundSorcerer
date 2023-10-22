package Interaction;

import java.io.IOException;
import Models.SongInfo;

public class ConsoleInteraction {
    
    private LibraryManager libraryManager;

    public ConsoleInteraction(LibraryManager libraryManager) {
        super();
        this.libraryManager = libraryManager;

        printMainMenu();

    }

    private void printMainMenu(){

        clearConsole();

        print("Press 1 to display all songs.");
        print("Press 2 to add a new song.");
        print("Press 3 to delete a song.");
        print("Press x to close.");

        String reponse = InputReader.getString();


        switch (reponse) {
            case "1":
                printLibrary();
                break;

            case "2":
                // TODO - Next logic
                break;
            
            case "3":
                // TODO - Next logic
                break;

            case "x":
                System.exit(0);
                break;

            default:
                break;
        }        
    }

    private void returnToMainMenu(){
        print("Press enter to return to the main menu...");

        // Hold console for input
        InputReader.getString();

        printMainMenu();
    }

    private void printLibrary(){

        clearConsole();

        int count = 1;

        for (SongInfo songInfo : libraryManager.AllSongs()) {
            print(count + ". " + songInfo.songName() + " " + songInfo.artistName());
            count++;
        }

        returnToMainMenu();
    }

    private void print(String text){
        System.out.println(text);
    }

    private void clearConsole(){
        try {

            // Create a ProcessBuilder to execute the "cls" command
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            Process process = processBuilder.inheritIO().start();

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {                
                // Console succesfully cleared.
            } else {
                System.err.println("Failed to clear the command prompt window.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
