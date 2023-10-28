package Persistence;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import HelperMethods.HelperMethods;
import Models.SongInfo;

public class SongLibrary{

    private final String LOCAL_APP_DATA = System.getenv("APPDATA");

    private Path appDataPath = Paths.get(LOCAL_APP_DATA, "SoundSorcerer");

    private List<SongInfo> allSongs = new ArrayList<SongInfo>();

    public SongLibrary() {
        super();

        if (Files.notExists(appDataPath)) {

            try {
                Files.createDirectories(appDataPath);
            } catch (Exception e) {
                System.out.println("Error: " + e.getLocalizedMessage());
            }            
        }

        RestoreSongs();

        //createDummySongs();
    }

    /** Exposes the list of all songs.
     * @return returns the list of songs which we have depersisted 
     */ 
    public List<SongInfo> AllSongs(){
        return allSongs;
    }    

    public void addSong(SongInfo song){
        allSongs.addLast(song);
        JsonHandler.saveToJson(song, appDataPath);
    }

    public void removeSong(int index){

        // Get the song from the given index
        SongInfo song = allSongs.get(index);

        // Delete the song from disk
        File songFile = new File(song.getFilePath());
        if (songFile.exists()) {            
            songFile.delete();
        }

        // Remove the song from the library
        allSongs.remove(song);
    }

    private void RestoreSongs(){

        File folder = new File(appDataPath.toString());
        File[] listOfFiles = folder.listFiles();
        
        if (listOfFiles.length > 1) {

            for (File file : listOfFiles) {

                // Check we have a file and it has an extension
                String fileExtension = HelperMethods.getFileExtension(file);
                if (file.isFile() && fileExtension != null) {

                    // Check if a .json before parsing into a SongInfo object.
                    if (fileExtension.equals(".json")) {

                        // Check we have managed to parse the json file,
                        // this could return null if the parsing has failed.
                        SongInfo restoredSong = JsonHandler.restoreSongInfo(file);
                        if (restoredSong != null) {
                            restoredSong.setFilePath(file.toString());
                            allSongs.addLast(restoredSong);
                        }
                    }
                }
            } // end of foreach

            System.out.println(allSongs.size() + " songs restored");
        }        
    }

    /** Method to create a list of dummy songs */
    @SuppressWarnings("unused")
    private void createDummySongs() {

        for (int i = 0; i < 5; i++) {

            String songName = "Song " + i;
            String artistName = "Artist " + i;
            int count = i * i;

            SongInfo dummy = new SongInfo(songName, artistName, count);

            JsonHandler.saveToJson(dummy, appDataPath);
        }
    }

}