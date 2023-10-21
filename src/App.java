import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.io.FileReader;

public class App {

    private static SongInfo song;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        song = new SongInfo("MySong", "Dave", 15);

        

        //saveToFile();

        //System.out.println("file saved!");

        //restoreData();
    }

    private static void saveToFile(){
        Gson gson = new GsonBuilder().create();

        String filePath = "C:\\Users\\fjcas\\source\\Git Projects\\data.json";

                
        try {

            Writer writer = new FileWriter(filePath);

            gson.toJson(song, writer);
            writer.flush();
            writer.close();


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void restoreData(){

        Gson gson = new Gson();

        String filePath = "C:\\Users\\fjcas\\source\\Git Projects\\data.json";

	

    try {
        
        FileReader reader = new FileReader(filePath);

        // 1. JSON file to Java object
	    SongInfo staff = gson.fromJson(reader, SongInfo.class);

        reader.close();

        System.out.println(song.artistName() + song.songName());

    } catch (Exception e) {
        System.out.println(e.toString());
    }

    

    }


}
