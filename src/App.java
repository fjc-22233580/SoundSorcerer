import Interaction.ConsoleInteraction;
import Interaction.LibraryManager;

/**
 * The entry point of the SoundSorcerer application.
 * This class initializes the LibraryManager and ConsoleInteraction objects, which are used
 * to manage the library and interact with the user through the console.
 */
public class App {

    public static void main(String[] args) throws Exception {   

        // Manages the library of sound files.        
        LibraryManager libraryManager = new LibraryManager();

        // Console interaction with the user.
        ConsoleInteraction ui = new ConsoleInteraction(libraryManager);
    }
}
