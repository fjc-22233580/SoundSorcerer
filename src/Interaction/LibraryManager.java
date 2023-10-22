package Interaction;

import Persistence.SongLibrary;
import java.util.List;
import Models.SongInfo;

public class LibraryManager {
    
    private SongLibrary songLibrary;

    public LibraryManager() {
        super();
        songLibrary = new SongLibrary();
    }

    public List<SongInfo> AllSongs(){
        return songLibrary.AllSongs();
    }

    public void addSong(String songTitle, String artistName, int playCount){

        SongInfo addedSong = new SongInfo(songTitle, artistName, playCount);

        songLibrary.AllSongs().addLast(addedSong);
    }

    public void removeSongAtIndex(int userIndex) {
        songLibrary.AllSongs().remove(userIndex);
    }
}
