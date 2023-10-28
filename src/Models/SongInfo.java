package Models;
public class SongInfo{    
   
    private String songName;   

    private String artistName;    

    private int playCount;    

    private String filePath;

    

    public SongInfo(String songName, String artistName, int playCount) {
        super();
        this.songName = songName;
        this.artistName = artistName;
        this.playCount = playCount;
    }

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
}
