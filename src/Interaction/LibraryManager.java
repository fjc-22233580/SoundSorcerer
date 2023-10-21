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
}
