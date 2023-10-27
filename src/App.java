import Interaction.ConsoleInteraction;
import Interaction.LibraryManager;

public class App {

    // TODO - Rehash text output to always keep library visible, then menus below
    // TODO - ASCII Art for intro
    // TODO - Update persistence when adding/removing songs
    // TODO - Add GUIDs to SongInfo?
    // TODO - Add sorting when displaying list
    // TODO - Other ideas?
    // Test commit

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        LibraryManager libraryManager = new LibraryManager();

        ConsoleInteraction ui = new ConsoleInteraction(libraryManager);
    }

}
