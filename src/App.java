import Interaction.ConsoleInteraction;
import Interaction.LibraryManager;

public class App {

    // TODO - Improve comments.
    // TODO - Add GUIDs to SongInfo?
    // TODO - Random generator for song info data?  
    // TODO - Improve uses of "returnToMainMenu" method by passing in the method to be taken to  

    public static void main(String[] args) throws Exception {
       
        LibraryManager libraryManager = new LibraryManager();

        ConsoleInteraction ui = new ConsoleInteraction(libraryManager);
    }
}
