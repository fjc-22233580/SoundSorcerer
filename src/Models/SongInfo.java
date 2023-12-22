package Models;

/**
 * This class is the model for song related information. 
 * Including holding its filepath which gets set after this is de-persisted.
 */
public class SongInfo{    
   
    // Fields that hold the relevant information for a song.
    private String songName; 
    private String artistName; 
    private int playCount;  
    private String filePath;  

    /** 
     * Constructor - instantiates this class with given args. 
     * @param songName The name of the song.
     * @param artistName The name of the artist.
     * @param playCount The number of times this song has been played. 
     */
    public SongInfo(String songName, String artistName, int playCount) {
        this.songName = songName;
        this.artistName = artistName;
        this.playCount = playCount;
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

    // #endregion
}
