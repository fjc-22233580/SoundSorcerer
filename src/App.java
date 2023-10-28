import Interaction.ConsoleInteraction;
import Interaction.LibraryManager;

public class App {

    // TODO - Rehash text output to always keep library visible, then menus below
    // TODO - ASCII Art for intro
    // TODO - Add GUIDs to SongInfo?
    // TODO - Add sorting when displaying list - into LibraryView class
    // TODO - Fix bug when pressing enter with no value, after first delete song prompt
    // TODO - Other ideas?

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        LibraryManager libraryManager = new LibraryManager();

        ConsoleInteraction ui = new ConsoleInteraction(libraryManager);
    }

}
