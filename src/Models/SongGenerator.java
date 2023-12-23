package Models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import HelperMethods.HelperMethods;

/**
 * The SongGenerator class is responsible for generating random songs
 * by selecting a random song title and artist name from predefined lists.
 * In the project folder there are two CSV files that contain a list of songs names and artist names.
 */
public class SongGenerator {
    
    // Lists to store the song titles and artist names
    private List<String> songTitles = new ArrayList<String>();
    private List<String> firstNames = new ArrayList<String>();
    private List<String> lastNames = new ArrayList<String>();

    // Path to the CSV files that contain the song titles and artist names
    private final Path SONG_TITLES_PATH = Paths.get(System.getProperty("user.dir"),"random", "RandomSongNames.csv");
    private final Path ARTIST_NAMES_PATH = Paths.get(System.getProperty("user.dir"),"random", "RandomArtistNames.csv");

    // The minimum and maximum number of plays a song can have
    private final int MIN_PLAY_COUNT = 1000;
    private final int MAX_PLAY_COUNT = 100000000;

    /**
     * Constructor for the SongGenerator class.
     * @throws RuntimeException If the source files do not exist.
     */
    public SongGenerator() {

        // Check if the source files exist
        if (Files.notExists(SONG_TITLES_PATH) || Files.notExists(ARTIST_NAMES_PATH)) {
            throw new RuntimeException("Source files do not exist");    
        }

        // Populate the song titles and artist names lists
        populateList(SONG_TITLES_PATH);
        populateList(ARTIST_NAMES_PATH);
    }

    /**
     * Generates a random song by selecting a random song title and artist name from the predefined lists.
     * @return A SongInfo object containing the song title, artist name, number of plays and a UUID.
     */
    public SongInfo generateRandomSong() {

        // Create a random number generator
        Random random = new Random();
        
        // Create a random song title and artist name
        String songTitle = songTitles.get(random.nextInt(songTitles.size()));
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String artistName = firstName + " " + lastName;

        UUID guid = UUID.randomUUID();
        
        SongInfo song = new SongInfo(songTitle, artistName, random.nextInt(MIN_PLAY_COUNT, MAX_PLAY_COUNT), guid);

        return song;
    }

    /**
     * Populates the list of song titles, first names, and last names from a CSV file.     * 
     * @param path the path to the CSV file
     */
    private void populateList(Path path) {
        
        File songTitlesFile = new File(path.toString());

        // Get and check the file extension, and that the file is a file. 
        String fileExtension = HelperMethods.getFileExtension(songTitlesFile);
        if (songTitlesFile.isFile() && fileExtension != null) {

            // If the file is a CSV file then read the file
            if(fileExtension.equalsIgnoreCase(".csv")) {

                try (BufferedReader br = new BufferedReader(new FileReader(songTitlesFile))) {    
                    
                    // Skip header line
                    String line = br.readLine(); 
                    
                    // Read the file line by line
                    while ((line = br.readLine()) != null) {

                        // Split the line into an array of strings
                        String[] splitLine = line.split(",");

                        // If we have more than 2 columns then add the first two columns to the firstNames and lastNames lists,
                        // otherwise add the first column to the songTitles list
                        if (splitLine.length > 1) {
                            firstNames.add(splitLine[0]); 
                            lastNames.add(splitLine[1]);
                        } else {
                            songTitles.add(splitLine[0]); 
                        }  
                    }
        
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }        
    }
}
