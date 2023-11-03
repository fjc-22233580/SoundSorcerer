import Interaction.ConsoleInteraction;
import Interaction.LibraryManager;

public class App {

    // TODO - Rehash text output to always keep library visible, then menus below
    // TODO - Add title 
    // TODO - Improve comments.
    // TODO - Add GUIDs to SongInfo?
    // TODO - Add sorting when displaying list - into LibraryView class
    // TODO - Fix bug when pressing enter with no value, after first delete song prompt
    // TODO - Other ideas?
    // TODO - Random generator for song info data?  
    // TODO - Improve uses of "returnToMainMenu" method by passing in the method to be taken to  

    public static void main(String[] args) throws Exception {
       
        LibraryManager libraryManager = new LibraryManager();

        ConsoleInteraction ui = new ConsoleInteraction(libraryManager);
    }

}
