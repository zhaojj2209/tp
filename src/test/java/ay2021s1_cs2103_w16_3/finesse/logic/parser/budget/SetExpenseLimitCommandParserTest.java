package ay2021s1_cs2103_w16_3.finesse.logic.parser.budget;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.AMOUNT_DESC_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.budget.SetExpenseLimitCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.budgetparsers.SetExpenseLimitCommandParser;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

public class SetExpenseLimitCommandParserTest {

    private SetExpenseLimitCommandParser parser = new SetExpenseLimitCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetExpenseLimitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no whitespace in front
        assertParseFailure(parser, PREFIX_AMOUNT + VALID_AMOUNT_INTERNSHIP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetExpenseLimitCommand.MESSAGE_USAGE));
        // preamble before amount prefix
        assertParseFailure(parser, "hello" + AMOUNT_DESC_INTERNSHIP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetExpenseLimitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSetExpenseLimitCommand() {
        assertParseSuccess(parser, AMOUNT_DESC_INTERNSHIP,
                new SetExpenseLimitCommand(new Amount(VALID_AMOUNT_INTERNSHIP)));
        // whitespaces before/after keywords
        assertParseSuccess(parser, "   " + AMOUNT_DESC_INTERNSHIP + "   ",
                new SetExpenseLimitCommand(new Amount(VALID_AMOUNT_INTERNSHIP)));
    }

}
