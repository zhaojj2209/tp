package ay2021s1_cs2103_w16_3.finesse.logic.parser.exceptions;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
