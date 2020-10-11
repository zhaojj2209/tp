package ay2021s1_cs2103_w16_3.finesse.logic.parser;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.TabCommand;

public class TabCommandParserTest {

    private TabCommandParser parser = new TabCommandParser();

    @Test
    public void parse_validArgs_returnsTabCommand() {
        assertParseSuccess(parser, "1", new TabCommand(Index.fromOneBased(1)));
        assertParseSuccess(parser, "2", new TabCommand(Index.fromOneBased(2)));
        assertParseSuccess(parser, "3", new TabCommand(Index.fromOneBased(3)));
        assertParseSuccess(parser, "4", new TabCommand(Index.fromOneBased(4)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2 hello", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
    }
}
