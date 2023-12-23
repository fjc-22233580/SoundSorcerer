package Persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import Models.SongInfo;

/**
 * The class provides methods for saving and restoring SongInfo objects to/from JSON files.
 */
public class JsonHandler {
    
    /**
     * Saves a SongInfo object into a JSON file at the specified path, using the song name as the file name.     * 
     * @param song The SongInfo object to be serialized into JSON.
     * @param folderPath The folder where the SongInfo object will be saved.
     */
    public static void saveToJson(SongInfo song, Path folderPath){

        // Create a Gson object (used to convert Java objects to JSON)
        // Note: This is a from the Gson library, not a class we created.
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        // Construct the file name for the JSON file
        String fileName = Paths.get(folderPath.toString(),song.getGuid().toString() + "." + song.getSongName() + ".json").toString();

        // Set the file path for the SongInfo object, this may be used later for file deletion.
        song.setFilePath(fileName);
        
        try {

            // Use Writer and GSON to write the JSON file
            Writer writer = new FileWriter(fileName);
            gson.toJson(song, writer);

            // Flush and close the writer - required for the file to be written
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restores a SongInfo object from a JSON file.
     * 
     * @param file The JSON file to restore the SongInfo object from.
     * @return The restored SongInfo object.
     */
    public static SongInfo restoreSongInfo(File file){
        
        SongInfo restoredSong = null;

        // Create a Gson object (used to convert Java objects to JSON)
        // Note: This is a from the Gson library, not a class we created.        
        Gson gson = new Gson();

        String songFilePath = file.toString();
        try {
        
            FileReader reader = new FileReader(songFilePath);            
            restoredSong = gson.fromJson(reader, SongInfo.class);
            restoredSong.setFilePath(songFilePath);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return restoredSong;
    }
}
