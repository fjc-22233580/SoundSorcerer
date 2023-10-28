package Interaction;

import BaseClasses.BaseView;
import Models.SongInfo;

public class LibraryView extends BaseView{    

    private LibraryManager libraryManager;

    public LibraryView(LibraryManager libraryManager) {
        super();
        this.libraryManager = libraryManager;
    }

    public void printSongs() {
        int count = 1;

        for (SongInfo songInfo : libraryManager.AllSongs()) {
            print(count + ". " + songInfo.getSongName() + " " + songInfo.getArtistName() + " " + songInfo.getPlayCount());
            count++;
        }
    }



}
