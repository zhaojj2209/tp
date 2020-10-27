package ay2021s1_cs2103_w16_3.finesse.logic.parser.budget;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
        assertParseFailure(parser, "a/20", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetExpenseLimitCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "hello a/-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetExpenseLimitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSetExpenseLimitCommand() {
        assertParseSuccess(parser, " a/500", new SetExpenseLimitCommand(new Amount("500")));
        assertParseSuccess(parser, " a/314.15", new SetExpenseLimitCommand(new Amount("314.15")));
        assertParseSuccess(parser, " a/$300", new SetExpenseLimitCommand(new Amount("$300")));
        assertParseSuccess(parser, " a/$123.45", new SetExpenseLimitCommand(new Amount("$123.45")));
    }

}
