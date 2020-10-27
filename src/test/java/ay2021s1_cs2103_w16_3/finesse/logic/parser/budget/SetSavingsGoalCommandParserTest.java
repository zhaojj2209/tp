package ay2021s1_cs2103_w16_3.finesse.logic.parser.budget;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.budget.SetSavingsGoalCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.budgetparsers.SetSavingsGoalCommandParser;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

public class SetSavingsGoalCommandParserTest {

    private SetSavingsGoalCommandParser parser = new SetSavingsGoalCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetSavingsGoalCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a/20", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetSavingsGoalCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "hello a/-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetSavingsGoalCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSetSavingsGoalCommand() {
        assertParseSuccess(parser, " a/500", new SetSavingsGoalCommand(new Amount("500")));
        assertParseSuccess(parser, " a/314.15", new SetSavingsGoalCommand(new Amount("314.15")));
        assertParseSuccess(parser, " a/$300", new SetSavingsGoalCommand(new Amount("$300")));
        assertParseSuccess(parser, " a/$123.45", new SetSavingsGoalCommand(new Amount("$123.45")));
    }

}
