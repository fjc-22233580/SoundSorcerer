package BaseClasses;

import java.io.IOException;
import java.util.List;

import Models.SongInfo;

public class BaseView {   

    /** Prints the given text followed a new line char.
     * @param text The string to print.
     */
    public void print(String text) {
        System.out.println(text);
    }

    public void printTitleBar(){
        print("#### SOUNDS SORCERER ####");
        print(" ");
    }

    public void printSong(SongInfo song){
        print(song.getSongName() + " " + song.getArtistName() + " " + song.getPlayCount());
    }

    public void printSongs(List<SongInfo> songs) {
        int count = 1;

        for (SongInfo songInfo : songs) {
            print(count + ". " + songInfo.getSongName() + " " + songInfo.getArtistName() + " " + songInfo.getPlayCount());
            count++;
        }
    }


    public void clearConsole(){

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
