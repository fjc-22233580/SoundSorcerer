package Models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import HelperMethods.HelperMethods;
import java.util.Random;
import java.util.UUID;

public class SongGenerator {
    
    private List<String> songTitles = new ArrayList();
    private List<String> firstNames = new ArrayList();
    private List<String> lastNames = new ArrayList();

    private final Path SONG_TITLES_PATH = Paths.get(System.getProperty("user.dir"),"random", "RandomSongNames.csv");
    private final Path ARTIST_NAMES_PATH = Paths.get(System.getProperty("user.dir"),"random", "RandomArtistNames.csv");

    public SongGenerator() {

        if (Files.notExists(SONG_TITLES_PATH) || Files.notExists(ARTIST_NAMES_PATH)) {
            throw new RuntimeException("Source files do not exist");    
        }

        populateSongTitles();  
        populateArtistNames();
    }

    public SongInfo generateRandomSong() {

        // Create a random number generator
        Random random = new Random();
        
        // Create a random song title and artist name
        String songTitle = songTitles.get(random.nextInt(songTitles.size()));
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String artistName = firstName + " " + lastName;

        UUID guid = UUID.randomUUID();
        
        SongInfo song = new SongInfo(songTitle, artistName, random.nextInt(1000, 10000000), guid);

        return song;
    }


    private void populateSongTitles() {

        // Get a list of all files in the songs folder
        File songTitlesFile = new File(SONG_TITLES_PATH.toString());

        String fileExtension = HelperMethods.getFileExtension(songTitlesFile);
        if (songTitlesFile.isFile() && fileExtension != null) {

            if(fileExtension.equalsIgnoreCase(".csv")) {

                try (BufferedReader br = new BufferedReader(new FileReader(songTitlesFile))) {  
        
                    String line;
                    
                    while ((line = br.readLine()) != null) {
                        
                        songTitles.add(line); 
                    }
        
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }        
    }

    private void populateArtistNames() {

        // Get a list of all files in the songs folder
        File songTitlesFile = new File(ARTIST_NAMES_PATH.toString());

        String fileExtension = HelperMethods.getFileExtension(songTitlesFile);
        if (songTitlesFile.isFile() && fileExtension != null) {

            if(fileExtension.equalsIgnoreCase(".csv")) {

                try (BufferedReader br = new BufferedReader(new FileReader(songTitlesFile))) {  
        
                    br.readLine(); // Skip header line

                    String line;
                    
                    while ((line = br.readLine()) != null) {

                        String[] splitLine = line.split(",");
                        
                        firstNames.add(splitLine[0]); 
                        lastNames.add(splitLine[1]);
                    }
        
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }        
    }
}
