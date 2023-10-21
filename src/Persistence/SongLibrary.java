package Persistence;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
     * 
     * @return returns the list of songs which we have depersisted 
     */ 
    public List<SongInfo> AllSongs(){
        return allSongs;
    }    

    private void RestoreSongs(){

        File folder = new File(appDataPath.toString());
        File[] listOfFiles = folder.listFiles();
        
        if (listOfFiles.length > 1) {

            for (File file : listOfFiles) {

                String fileExtension = getFileExtension(file);

                // Check if we have a file and if it a .json before parsing into a SongInfo object.
                if (file.isFile() && fileExtension.equals(".json")) {

                    // Check we have managed to parse the json file,
                    // this could return null if the parsing has failed.
                    SongInfo restoredSong = JsonHandler.restoreSongInfo(file);
                    if (restoredSong != null) {                        
                        allSongs.addLast(restoredSong);
                    }
                }
            }

            System.out.println(allSongs.size() + " songs restored");
        }
        
    }

    /** Helper method to get a file extension of a file.
     * 
     * @param file which extension will be extracted.
     * @return the extension of the file
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /** Method to create a list of dummy songs */
    @SuppressWarnings("unused")
    private void createDummySongs(){


        for (int i = 0; i < 5; i++) {
            
            String songName = "Song " + i;
            String artistName = "Artist " + i;
            int count = i * i;

            SongInfo dummy = new SongInfo(songName, artistName, count);

            JsonHandler.saveToJson(dummy, appDataPath.toString());
        }
    }

}