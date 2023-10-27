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
            // Ignore failed parsing
        }

        return value;
    }

    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
