package Interaction;
import java.util.Scanner;

/**
* This class provides methods to get user input from the console, 
* it currently supports string, int, and double.
*/
public class InputReader {

    private static Scanner reader = new Scanner(System.in);

    /**
    * Returns the string that the user has entered.
    */
    public static String getString(){
        String inputStr = reader.nextLine();
        return inputStr;      
    }

    /**
    * Returns the integer that the user has entered.
    */
    public static Integer getInt(){
        Integer inputInt = reader.nextInt();
        return inputInt;      
    }

    /**
    * Returns the double that the user has entered.
    */
    public static Double getDouble(){
        double inputStr = reader.nextDouble();
        return inputStr;      
    }

    
}
