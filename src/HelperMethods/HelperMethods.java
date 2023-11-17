package HelperMethods;

import java.io.File;

public class HelperMethods {

    /** Helper method which attempts to parse given text to an integer
     * 
     * @param text the text to be parsed to an integer
     * @return Returns the parsed integer or null if we couldn't parse the given text.
     */
    public static Integer tryParseInt(String text) {

        // Assume we couldn't parse our supplied text so return null.
        Integer value = null;

        try {

            value = Integer.parseInt(text);

        } catch (NumberFormatException ex) {
            // Ignore failed parsing as we will return null anyway
        }

        return value;
    }

    /** Check that a given strings contains atleast 1 alphabetical character
     * @param text the given text
     * @return true if string contains an alphabetical character, else false.
     */
    public static boolean containsAlphabet(String text){
       
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return true;
            }
        }

        return false;
    }

    /** Helper method which attempts to parse given text to e double
     * 
     * @param text the text to be parsed to an double
     * @return Returns the parsed double or null if we couldn't parse the given text.
     */
    public static Double tryParseDouble(String text) {

        // Assume we couldn't parse our supplied text so return null.
        Double value = null;

        try {

            value = Double.parseDouble(text);

        } catch (NumberFormatException ex) {
            // Ignore failed parsing as we will return null anyway
        }

        return value;
    }

    /** Helper method to determine if a string is null or empty
     * 
     * @param string the text to be processed
     * @return Returns true if string empty or is null, else returns false.
     */
    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }


    /** Helper method to get a file extension of a file.
     * If we couldn't find an extention this will null;
     * 
     * @param file which extension will be extracted.
     * @return the extension of the file
     */
    public static String getFileExtension(File file) {
        
        // Assume we couldn't get an extension for some reason.
        String ext = null;

        // Get the extension from the file.
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf > 0) {
            ext = name.substring(lastIndexOf);
        }

        return ext;
    }

}
