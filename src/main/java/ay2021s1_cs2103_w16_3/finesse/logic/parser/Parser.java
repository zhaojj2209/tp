package ay2021s1_cs2103_w16_3.finesse.logic.parser;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.Command;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
