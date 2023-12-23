package Models;
import java.util.UUID;

import com.google.gson.annotations.Expose;

/**
 * This class is the model for song related information. 
 * Including holding its filepath which gets set after this is de-persisted.
 */
public class SongInfo{    
   
    // Fields that hold the relevant information for a song, and are exposed to Gson so will be saved/restored
    @Expose
    private String songName; 
    @Expose
    private String artistName; 
    @Expose
    private int playCount;  
    @Expose
    private UUID guid;  

    // The file path for the song, this is not exposed to Gson so will not be saved/restored,
    // but is used to delete the file when the song is removed from the library.
    private String filePath;
    
    /** 
     * Constructor - instantiates this class with given args. 
     * @param songName The name of the song.
     * @param artistName The name of the artist.
     * @param playCount The number of times this song has been played. 
     * @param guid The guid for this song.
     */
    public SongInfo(String songName, String artistName, int playCount, UUID guid) {
        this.songName = songName;
        this.artistName = artistName;
        this.playCount = playCount;
        this.guid = guid;
    }
    
    // #region Song Info Getters and Setters
    
    public String getSongName() {
        return songName;
    }
    
    public String getArtistName() {
        return artistName;
    }
    
    public int getPlayCount() {
        return playCount;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public UUID getGuid() {
        return guid;
    }
    
    // #endregion
}
