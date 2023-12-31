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
        print("#### SOUND SORCERER ####");
        print(" ");
    }
    
    /** Prints all variables for a given song.
     */
    protected void printSong(SongInfo song){
        print(song.getSongName() + " - " + song.getArtistName() + " | " + song.getPlayCount());
    }

    /**
     * Prints all song info for each song in the given list,
     * will be printed in a table format with headers and row numbers.
     * @param songs the list of songs to be printed
     */
    protected void printSongsInTableFormat(List<SongInfo> songs) {

        // Table headers
        final String SONG_NAME = "Song Name";
        final String ARTIST_NAME = "Artist Name";
        final String PLAY_COUNT = "Play Count";

        // Song Count
        int count = 1;

        // Max string lengths - used to calculate padding
        int maxSongNameWidth = 0;
        int maxSongArtistWidth = 0;

        // Find the max string lengths for each column
        for (SongInfo songInfo : songs) {

            int currentSongNameWidth = songInfo.getSongName().length();
            if (currentSongNameWidth > maxSongNameWidth) {
                maxSongNameWidth = currentSongNameWidth;                
            }
            
            int currentSongArtistWidth = songInfo.getArtistName().length();
            if (currentSongArtistWidth > maxSongArtistWidth) {
                maxSongArtistWidth = currentSongArtistWidth;                
            }            
        }

        // Print the table headers with padding
        String namePadding = getPadding(SONG_NAME, maxSongNameWidth);
        String titlePadding = getPadding(ARTIST_NAME, maxSongArtistWidth);
        print("    " + SONG_NAME + namePadding + "| " + ARTIST_NAME + titlePadding + "| " + PLAY_COUNT);

        // Print each song with padding - so it appears like a table
        for (SongInfo songInfo : songs) {

            String songNamePadding = getPadding(songInfo.getSongName(), maxSongNameWidth);
            String songArtistPadding = getPadding(songInfo.getArtistName(), maxSongArtistWidth);  
            
            // Format the row number and play count with commas and leading zeros
            String formattedRowNumber = String.format("%02d", count);
            String formattedPlayCount = String.format("%,d", songInfo.getPlayCount());
            
            print(formattedRowNumber + ": " + songInfo.getSongName() + songNamePadding + "| " + songInfo.getArtistName() + songArtistPadding + "| " + formattedPlayCount);
            count++;
        }
    }
    
    /**
     * Returns a string of spaces used for padding.
     * The padding will be different depending on the length of the input text.
     * @param text      the input text
     * @param maxLength the maximum length of the resulting string
     * @return the padding string
     */
    private String getPadding(String text, int maxLength) {

        int spaces = maxLength - text.length() + 1; 
        String padding = String.format("%" + spaces + "s", "");
        return padding;
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
