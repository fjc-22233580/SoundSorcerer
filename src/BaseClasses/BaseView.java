package BaseClasses;

import java.io.IOException;
import java.util.List;
import Models.SongInfo;

/**
 * The BaseView class (abstract) represents the base view for the SoundSorcerer ConsoleInteraction and LibraryView .
 * It provides methods for printing text, displaying the title bar, printing song information,
 * printing a list of songs, and clearing the console.
 */
public abstract class BaseView {   

    /** Prints the given text followed a new line char.
     * @param text The string to print.
     */
    protected void print(String text) {
        System.out.println(text);
    }

    /** Prints the title bar - used to keep the title above all other output.
     */
    protected void printTitleBar(){
        print("#### SOUNDS SORCERER ####");
        print(" ");
    }
    
    /** Prints all variables for a given song.
     */
    protected void printSong(SongInfo song){
        print(song.getSongName() + " " + song.getArtistName() + " " + song.getPlayCount());
    }

    /**
     * Prints song infor for each song list.     *
     * @param songs the list of songs to be printed
     */
    protected void printSongs(List<SongInfo> songs) {
        int count = 1;

        for (SongInfo songInfo : songs) {
            print(count + ". " + songInfo.getSongName() + " " + songInfo.getArtistName() + " " + songInfo.getPlayCount());
            count++;
        }
    }

    /**
     * Clears the console window.
     * This method uses the "cls" command in the Windows command prompt to clear the console.
     * If the command execution fails, an error message is printed.
     */
    protected void clearConsole(){

        try {
            // Create a ProcessBuilder to execute the "cls" command
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            Process process = processBuilder.inheritIO().start();

            // Wait for the process to complete
            int exitCode = process.waitFor();

            // Report an error incase we couldn't clear for some reason
            if (exitCode != 0) { print("Failed to clear the command prompt window."); }

        } catch (IOException | InterruptedException exc) {
            print("Unknown error - please restart!");
            exc.printStackTrace();
        }
    }
}
