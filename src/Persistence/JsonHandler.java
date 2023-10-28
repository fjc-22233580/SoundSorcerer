package Persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import Models.SongInfo;

/** Class used to persist and depersist song info objects to and from JSON */
public class JsonHandler {
    
    /** Saves a SongInfo into a json file at the specific path,
     * using the song name as the file name 
     * @param song The SongInfo to be serialised into JSON.
     * @param folderPath The folder where the given SongInfo will be saved.
     */
    public static void saveToJson(SongInfo song, Path folderPath){

        Gson gson = new GsonBuilder().create();

        String fileName = Paths.get(folderPath.toString(), song.getSongName() + ".json").toString();
        song.setFilePath(fileName);
        
        try {

            Writer writer = new FileWriter(fileName);

            gson.toJson(song, writer);
            writer.flush();
            writer.close();


        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static SongInfo restoreSongInfo(File file){
        
        SongInfo song = null;
        
        Gson gson = new Gson();

        String songFilePath = file.toString();

        try {
        
            FileReader reader = new FileReader(songFilePath);

            // 1. JSON file to Java object
            song = gson.fromJson(reader, SongInfo.class);
            reader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return song;
    }
}
