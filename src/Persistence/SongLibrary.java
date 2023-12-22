package Persistence;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import HelperMethods.HelperMethods;
import Models.SongInfo;

/**
 * The SongLibrary class represents a library of songs in the SoundSorcerer application.
 * It provides methods to add and remove songs, as well as retrieve the list of all songs.
 * This class also handles restoring songs from disk on application startup,
 * and saving songs to disk when they are added to the library.
 * On the disk songs are stored in the songs folder as JSON files.
 */
public class SongLibrary{

    /** The list of all songs in the library. */
    private List<SongInfo> allSongs = new ArrayList<SongInfo>();

    /** The path to the songs folder. 
     * Note: The creation of this property should retrieve the relative path to the songs folder,
     * regardless of the operating system the application is running on.
     * ... Hopefully!
    */
    private final Path songsPath = Paths.get(System.getProperty("user.dir"), "songs");

    /** Constructor for the SongLibrary class. 
     * First checks the songs folder existense, if it doesn't exist then a runtime exception will be thrown.
     * Then we restore all songs from the songs folder.
     */
    public SongLibrary() {

        if (Files.notExists(songsPath)) {
            throw new RuntimeException("Songs folder does not exist");                   
        }

        restoreAllSongs();
    }

    /** Exposes the list of all songs.
     * @return returns the list of songs which we have depersisted 
     */ 
    public List<SongInfo> AllSongs(){
        return allSongs;
    }    

    /**
     * Adds a song to the song library and saves it to disk as JSON.  
     * @param song the song to be added
     */
    public void addSong(SongInfo song){
        allSongs.add(song);
        JsonHandler.saveToJson(song, songsPath);
    }

    /**
     * Removes a song from the song library and deletes it from disk.
     * @param index the index of the song to be removed
     */
    public void removeSong(int index){

        // Get the song from the given index
        // Note: The index has been validated by the calling method
        SongInfo song = allSongs.get(index);

        // Delete the song from disk
        // Each instance of SongInfo has a property which stores its own file path
        File songFile = new File(song.getFilePath());
        if (songFile.exists()) {            
            songFile.delete();
        }

        // Remove the song from the library
        allSongs.remove(song);
    }


    /** Handles restoring all songs from disk. */
    private void restoreAllSongs(){

        // Get a list of all files in the songs folder
        File folder = new File(songsPath.toString());
        File[] listOfFiles = folder.listFiles();
        
        // Check we have files in the folder
        if (listOfFiles.length > 0) {

            // Iterate through each file in the folder
            for (File file : listOfFiles) {

                // Check we have a file and it has an extension
                String fileExtension = HelperMethods.getFileExtension(file);
                if (file.isFile() && fileExtension != null) {

                    // Check if the file is json before parsing into a SongInfo object.
                    if (fileExtension.equalsIgnoreCase(".json")) {

                        // Check we have managed to parse the json file,
                        // this could return null if the parsing has failed.
                        SongInfo restoredSong = JsonHandler.restoreSongInfo(file);
                        if (restoredSong != null) {
                            
                            // Set the songs file path and then add it to the list of songs
                            restoredSong.setFilePath(file.toString());
                            allSongs.add(restoredSong);
                        }
                    }
                }
            } 

            System.out.println(allSongs.size() + " songs restored");
        }        
    }

    /** Method to create a list of dummy songs 
     * Note: This method is only used for testing purposes.
    */
    @SuppressWarnings("unused")
    private void createDummySongs() {

        for (int i = 0; i < 5; i++) {

            String songName = "Song " + i;
            String artistName = "Artist " + i;
            int count = i * i;

            SongInfo dummy = new SongInfo(songName, artistName, count);

            JsonHandler.saveToJson(dummy, songsPath);
        }
    }

}