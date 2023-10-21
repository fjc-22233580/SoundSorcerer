package Persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import Models.SongInfo;

public class JsonHandler {
    
    public static void saveToJson(SongInfo song, String folderPath){

        Gson gson = new GsonBuilder().create();

        String fileName = Paths.get(folderPath, song.songName() + ".json").toString();
        
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
