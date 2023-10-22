package Interaction;

import java.io.IOException;
import Models.SongInfo;
import HelperMethods.HelperMethods;

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
                addNewSong();
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

    private void returnToMainMenu(String extraMessage){
        print(extraMessage);
        print("Press enter to return to the main menu...");

        // Hold console for input
        InputReader.getString();

        printMainMenu();
    }

    private void returnToMainMenu(){
        print("Press enter to return to the main menu...");

        // Hold console for input
        InputReader.getString();

        printMainMenu();
    }

    private void addNewSong(){

        boolean invalidArgs = false;

        clearConsole();

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

        int count = 1;

        for (SongInfo songInfo : libraryManager.AllSongs()) {
            print(count + ". " + songInfo.songName() + " " + songInfo.artistName() + " " + songInfo.playCount());
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
