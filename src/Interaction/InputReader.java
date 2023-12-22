package Interaction;
import java.util.Scanner;
import HelperMethods.HelperMethods;

/**
* This class provides methods to get user input from the console, 
* it currently supports string, and int.
*/
public class InputReader {

    // Scanner object to read user input from the console.
    private static Scanner reader = new Scanner(System.in);

    /**
    * Returns the string that the user has entered.
    */
    public static String getString(){
        return getLine();      
    }

    /**
    * Returns the integer that the user has entered.
    * If the user has not entered an integer, it will return null.
    */
    public static Integer getInt(){
        Integer value = HelperMethods.tryParseInt(getLine());
        return value;      
    }

    /** Gets the next line from the terminal,
     * seperated into its own function because the scanners .nextInt() fails to consume the new line char so only works once.
     * Better to use nextLine() then convert into necessary types. 
     * @return the string that the user has input into the terminal.
     */
    private static String getLine(){
        String inputStr = reader.nextLine();
        return inputStr;
    }    
}
