package Persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.io.FileReader;

import Models.SongInfo;

public class JsonHandler {
    
    public static void saveToJson(SongInfo song){

        Gson gson = new GsonBuilder().create();

        String filePath = "C:\\Users\\fjcas\\source\\UniWork\\SoundSorcerer\\data.json";

                
        try {

            Writer writer = new FileWriter(filePath);

            gson.toJson(song, writer);
            writer.flush();
            writer.close();


        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static SongInfo restoreSongInfo(){
        
        SongInfo song = null;
        
        Gson gson = new Gson();

        String filePath = "C:\\Users\\fjcas\\source\\UniWork\\SoundSorcerer\\data.json";

	

        try {
        
            FileReader reader = new FileReader(filePath);

            // 1. JSON file to Java object
            song = gson.fromJson(reader, SongInfo.class);

            reader.close();

            System.out.println(song.artistName() + song.songName());

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return song;
    }
}
