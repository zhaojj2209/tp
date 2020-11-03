package ay2021s1_cs2103_w16_3.finesse.commons.util;

import static ay2021s1_cs2103_w16_3.finesse.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code keyphrase}.
     *   Ignores case, but a full keyphrase match is required.
     *   <br>examples:<pre>
     *       containsIgnoreCase("ABc def", "abc") == true
     *       containsIgnoreCase("ABc def", "DEF") == true
     *       containsIgnoreCase("ABc def", "AB") == false //not a full keyphrase match
     *       </pre>
     * @param sentence cannot be null
     * @param keyphrase cannot be null, cannot be empty
     */
    public static boolean containsIgnoreCase(String sentence, String keyphrase) {
        requireNonNull(sentence);
        checkEmptyString(keyphrase);

        String preppedWord = keyphrase.trim();
        return sentence.toLowerCase().contains(preppedWord.toLowerCase());
    }

    /**
     * Checks if the string {@code s} is an empty string.
     * A string is considered empty if it is the empty string, or is a string consisting of only whitespaces.
     * @param s cannot be null
     */
    public static void checkEmptyString(String s) {
        requireNonNull(s);
        checkArgument(!s.trim().isEmpty(), "Parameter cannot be empty");
    }

    /**
     * Returns a detailed message of the Throwable object {@code t}, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
