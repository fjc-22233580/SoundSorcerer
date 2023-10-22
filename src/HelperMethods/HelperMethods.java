package HelperMethods;

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
            // Ignore failed parsing
        }

        return value;
    }

    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
