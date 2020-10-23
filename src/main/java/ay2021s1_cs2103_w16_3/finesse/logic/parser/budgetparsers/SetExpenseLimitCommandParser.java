package ay2021s1_cs2103_w16_3.finesse.logic.parser.budgetparsers;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.budget.SetExpenseLimitCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.Parser;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.ParserUtil;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.exceptions.ParseException;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SetExpenseLimitCommandParser implements Parser<SetExpenseLimitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetExpenseLimitCommand parse(String args) throws ParseException {
        try {
            Amount amount = ParserUtil.parseAmount(args);
            return new SetExpenseLimitCommand(amount);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetExpenseLimitCommand.MESSAGE_USAGE), pe);
        }
    }

}
