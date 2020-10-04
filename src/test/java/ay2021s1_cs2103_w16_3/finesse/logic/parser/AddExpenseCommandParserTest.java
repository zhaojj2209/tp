package ay2021s1_cs2103_w16_3.finesse.logic.parser;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.AMOUNT_DESC_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.AMOUNT_DESC_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.CATEGORY_DESC_FRIEND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.CATEGORY_DESC_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_FRIEND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.AMY;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.BOB;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.AddExpenseCommand;
import ay2021s1_cs2103_w16_3.finesse.model.category.Category;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Name;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class AddExpenseCommandParserTest {
    private AddExpenseCommandParser parser = new AddExpenseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Expense expectedExpense = new TransactionBuilder(BOB).withCategories(VALID_CATEGORY_FRIEND).buildExpense();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + AMOUNT_DESC_BOB + DATE_DESC_BOB
                + CATEGORY_DESC_FRIEND, new AddExpenseCommand(expectedExpense));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + AMOUNT_DESC_BOB + DATE_DESC_BOB
                + CATEGORY_DESC_FRIEND, new AddExpenseCommand(expectedExpense));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AMOUNT_DESC_AMY + AMOUNT_DESC_BOB + DATE_DESC_BOB
                + CATEGORY_DESC_FRIEND, new AddExpenseCommand(expectedExpense));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AMOUNT_DESC_BOB + DATE_DESC_AMY + DATE_DESC_BOB
                + CATEGORY_DESC_FRIEND, new AddExpenseCommand(expectedExpense));

        // multiple categories - all accepted
        Expense expectedExpenseMultipleCategories = new TransactionBuilder(BOB)
                .withCategories(VALID_CATEGORY_FRIEND, VALID_CATEGORY_HUSBAND).buildExpense();
        assertParseSuccess(parser, NAME_DESC_BOB + AMOUNT_DESC_BOB + DATE_DESC_BOB + CATEGORY_DESC_HUSBAND
                + CATEGORY_DESC_FRIEND, new AddExpenseCommand(expectedExpenseMultipleCategories));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero categories
        Expense expectedExpense = new TransactionBuilder(AMY).withCategories().buildExpense();
        assertParseSuccess(parser, NAME_DESC_AMY + AMOUNT_DESC_AMY + DATE_DESC_AMY,
                new AddExpenseCommand(expectedExpense));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExpenseCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + AMOUNT_DESC_BOB + DATE_DESC_BOB, expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_AMOUNT_BOB + DATE_DESC_BOB, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + AMOUNT_DESC_BOB + VALID_DATE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_AMOUNT_BOB + VALID_DATE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + AMOUNT_DESC_BOB + DATE_DESC_BOB
                + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_AMOUNT_DESC + DATE_DESC_BOB
                + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND, Amount.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + AMOUNT_DESC_BOB + INVALID_DATE_DESC
                + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, NAME_DESC_BOB + AMOUNT_DESC_BOB + DATE_DESC_BOB
                + INVALID_CATEGORY_DESC + VALID_CATEGORY_FRIEND, Category.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + AMOUNT_DESC_BOB + INVALID_DATE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + AMOUNT_DESC_BOB + DATE_DESC_BOB
                        + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExpenseCommand.MESSAGE_USAGE));
    }
}
