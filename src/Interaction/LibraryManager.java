package Interaction;
import Persistence.SongLibrary;
import java.util.List;
import Models.SongInfo;

/**
 * The LibraryManager class is responsible for managing the song library.
 * It contains methods to retrieve all songs, create a new song, and remove a
 * song from the list of all songs.
 */
public class LibraryManager {

    /** The SongLibrary object which contains the list of all songs */
    private SongLibrary songLibrary;

    public LibraryManager() {
        super();
        songLibrary = new SongLibrary();
    }

    /**
     * Retrieves a list of all songs in the library. * 
     */
    public List<SongInfo> getAllSongs() {
        return songLibrary.AllSongs();
    }

    /**
     * Creates a new song with the specified title, artist name, and play count,
     * and adds it to the library.
     * 
     * @param songTitle  the title of the song
     * @param artistName the name of the artist
     * @param playCount  the play count of the song
     */
    public void createSong(String songTitle, String artistName, int playCount) {

        SongInfo addedSong = new SongInfo(songTitle, artistName, playCount);
        songLibrary.addSong(addedSong);
    }

    /**
     * Removes the song at the specified index from list of all songs     * 
     * @param userIndex the user supplied index.
     */
    public void removeSongAtIndex(int userIndex) {
        songLibrary.removeSong(userIndex);
    }
}
