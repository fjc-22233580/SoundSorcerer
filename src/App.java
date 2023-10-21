import Interaction.ConsoleInteraction;
import Interaction.LibraryManager;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        LibraryManager libraryManager = new LibraryManager();

        ConsoleInteraction ui = new ConsoleInteraction(libraryManager);
    }

}
