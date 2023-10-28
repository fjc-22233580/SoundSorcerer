package Interaction;

import Models.SongInfo;

public class LibraryView {

    

    private LibraryManager libraryManager;

    public LibraryView(LibraryManager libraryManager) {
        super();
        this.libraryManager = libraryManager;
    }

    public void printSongs() {
        int count = 1;

        for (SongInfo songInfo : libraryManager.AllSongs()) {
            System.out.println(count + ". " + songInfo.getSongName() + " " + songInfo.getArtistName() + " " + songInfo.getPlayCount());
            count++;
        }
    }



}
