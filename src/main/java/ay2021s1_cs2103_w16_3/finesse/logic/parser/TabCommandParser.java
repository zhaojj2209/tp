package ay2021s1_cs2103_w16_3.finesse.logic.parser;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.TabCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code TabCommand} object.
 */
public class TabCommandParser implements Parser<TabCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code TabCommand}
     * and returns a {@code TabCommand} object for execution.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public TabCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TabCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE), pe);
        }
    }
}
