package ay2021s1_cs2103_w16_3.finesse.logic.parser;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.AMOUNT_DESC_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.AMOUNT_DESC_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.CATEGORY_DESC_FOOD_BEVERAGE;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.CATEGORY_DESC_WORK;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DATE_DESC_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DATE_DESC_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.TITLE_DESC_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.TITLE_DESC_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_FOOD_BEVERAGE;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_WORK;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_INTERNSHIP;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT_FROM;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT_TO;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE_TO;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_TITLE;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_AMOUNT_DUPLICATE_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_AMOUNT_FROM_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_AMOUNT_RANGE_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_AMOUNT_SEARCH_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_AMOUNT_TO_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_DATE_DUPLICATE_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_DATE_FROM_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_DATE_RANGE_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_DATE_SEARCH_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_DATE_TO_CONSTRAINTS;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.FindCommandParser.MESSAGE_EMPTY_TITLE_KEYPHRASE;
import static ay2021s1_cs2103_w16_3.finesse.model.category.Category.MESSAGE_EMPTY_CATEGORY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.FindCommand;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.HasCategoriesPredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.HasExactAmountPredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.InAmountRangePredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.InDateRangePredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.OnExactDatePredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.TitleContainsKeyphrasesPredicate;

public class FindCommandParserTest {

    // These strings are only used in FindCommandParserTest, hence are defined here instead of in CommandTestUtil
    public static final String AMOUNT_FROM_DESC_BUBBLE_TEA = " " + PREFIX_AMOUNT_FROM + VALID_AMOUNT_BUBBLE_TEA;
    public static final String AMOUNT_TO_DESC_BUBBLE_TEA = " " + PREFIX_AMOUNT_TO + VALID_AMOUNT_BUBBLE_TEA;
    public static final String AMOUNT_FROM_DESC_INTERNSHIP = " " + PREFIX_AMOUNT_FROM + VALID_AMOUNT_INTERNSHIP;
    public static final String AMOUNT_TO_DESC_INTERNSHIP = " " + PREFIX_AMOUNT_TO + VALID_AMOUNT_INTERNSHIP;
    public static final String DATE_FROM_DESC_BUBBLE_TEA = " " + PREFIX_DATE_FROM + VALID_DATE_BUBBLE_TEA;
    public static final String DATE_TO_DESC_BUBBLE_TEA = " " + PREFIX_DATE_TO + VALID_DATE_BUBBLE_TEA;
    public static final String DATE_FROM_DESC_INTERNSHIP = " " + PREFIX_DATE_FROM + VALID_DATE_INTERNSHIP;
    public static final String DATE_TO_DESC_INTERNSHIP = " " + PREFIX_DATE_TO + VALID_DATE_INTERNSHIP;

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validTitleKeyphrases_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        // one title keyphrase
        predicateList.add(new TitleContainsKeyphrasesPredicate(Arrays.asList(VALID_TITLE_BUBBLE_TEA)));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, TITLE_DESC_BUBBLE_TEA, expectedFindCommand);

        // multiple title keyphrases
        predicateList = new ArrayList<>();
        predicateList.add(new TitleContainsKeyphrasesPredicate(
                Arrays.asList(VALID_TITLE_BUBBLE_TEA, VALID_TITLE_INTERNSHIP)));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, TITLE_DESC_BUBBLE_TEA + TITLE_DESC_INTERNSHIP, expectedFindCommand);
    }

    @Test
    public void parse_validAmount_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        predicateList.add(new HasExactAmountPredicate(new Amount(VALID_AMOUNT_BUBBLE_TEA)));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, AMOUNT_DESC_BUBBLE_TEA, expectedFindCommand);
    }

    @Test
    public void parse_validDate_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        predicateList.add(new OnExactDatePredicate(new Date(VALID_DATE_BUBBLE_TEA)));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, DATE_DESC_BUBBLE_TEA, expectedFindCommand);
    }

    @Test
    public void parse_validCategories_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        // one category
        predicateList.add(new HasCategoriesPredicate(Arrays.asList(VALID_CATEGORY_FOOD_BEVERAGE)));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, CATEGORY_DESC_FOOD_BEVERAGE, expectedFindCommand);

        // multiple categories
        predicateList = new ArrayList<>();
        predicateList.add(new HasCategoriesPredicate(
                Arrays.asList(VALID_CATEGORY_FOOD_BEVERAGE, VALID_CATEGORY_WORK)));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, CATEGORY_DESC_FOOD_BEVERAGE + CATEGORY_DESC_WORK, expectedFindCommand);
    }

    @Test
    public void parse_validAmountRange_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        // VALID_AMOUNT_BUBBLE_TEA is less than VALID_AMOUNT_INTERNSHIP
        Optional<Amount> lowerBound = Optional.of(new Amount(VALID_AMOUNT_BUBBLE_TEA));
        Optional<Amount> upperBound = Optional.of(new Amount(VALID_AMOUNT_INTERNSHIP));

        // lower bound only
        predicateList.add(new InAmountRangePredicate(lowerBound, Optional.empty()));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, AMOUNT_FROM_DESC_BUBBLE_TEA, expectedFindCommand);

        // upper bound only
        predicateList = new ArrayList<>();
        predicateList.add(new InAmountRangePredicate(Optional.empty(), upperBound));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, AMOUNT_TO_DESC_INTERNSHIP, expectedFindCommand);

        // upper and lower bound
        predicateList = new ArrayList<>();
        predicateList.add(new InAmountRangePredicate(lowerBound, upperBound));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, AMOUNT_FROM_DESC_BUBBLE_TEA + AMOUNT_TO_DESC_INTERNSHIP, expectedFindCommand);

        // same upper and lower bound values
        predicateList = new ArrayList<>();
        predicateList.add(new InAmountRangePredicate(upperBound, upperBound));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, AMOUNT_FROM_DESC_INTERNSHIP + AMOUNT_TO_DESC_INTERNSHIP, expectedFindCommand);
    }

    @Test
    public void parse_validDateRange_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        // VALID_DATE_INTERNSHIP is earlier than VALID_DATE_BUBBLE_TEA
        Optional<Date> lowerBound = Optional.of(new Date(VALID_DATE_INTERNSHIP));
        Optional<Date> upperBound = Optional.of(new Date(VALID_DATE_BUBBLE_TEA));

        // lower bound only
        predicateList.add(new InDateRangePredicate(lowerBound, Optional.empty()));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, DATE_FROM_DESC_INTERNSHIP, expectedFindCommand);

        // upper bound only
        predicateList = new ArrayList<>();
        predicateList.add(new InDateRangePredicate(Optional.empty(), upperBound));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, DATE_TO_DESC_BUBBLE_TEA, expectedFindCommand);

        // upper and lower bound
        predicateList = new ArrayList<>();
        predicateList.add(new InDateRangePredicate(lowerBound, upperBound));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, DATE_FROM_DESC_INTERNSHIP + DATE_TO_DESC_BUBBLE_TEA, expectedFindCommand);

        // same upper and lower bound values
        predicateList = new ArrayList<>();
        predicateList.add(new InDateRangePredicate(upperBound, upperBound));
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, DATE_FROM_DESC_BUBBLE_TEA + DATE_TO_DESC_BUBBLE_TEA, expectedFindCommand);
    }

    @Test
    public void parse_multipleSearchParameters_success() {
        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        predicateList.add(new TitleContainsKeyphrasesPredicate(Arrays.asList(VALID_TITLE_BUBBLE_TEA)));
        predicateList.add(new HasExactAmountPredicate(new Amount(VALID_AMOUNT_BUBBLE_TEA)));
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, TITLE_DESC_BUBBLE_TEA + AMOUNT_DESC_BUBBLE_TEA, expectedFindCommand);
    }

    @Test
    public void parse_invalidCommandFormat_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, PREAMBLE_NON_EMPTY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hasEmptyArgs_failure() {
        // empty title keyphrase
        assertParseFailure(parser, " " + PREFIX_TITLE, MESSAGE_EMPTY_TITLE_KEYPHRASE);

        // empty title keyphrase followed by non-empty title keyphrase
        assertParseFailure(parser, " " + PREFIX_TITLE + TITLE_DESC_BUBBLE_TEA, MESSAGE_EMPTY_TITLE_KEYPHRASE);

        // empty category
        assertParseFailure(parser, " " + PREFIX_CATEGORY, MESSAGE_EMPTY_CATEGORY);

        // empty category followed by non-empty category
        assertParseFailure(parser, " " + PREFIX_CATEGORY + CATEGORY_DESC_FOOD_BEVERAGE, MESSAGE_EMPTY_CATEGORY);

        // multiple empty search parameters, first error message thrown
        assertParseFailure(parser, " " + PREFIX_TITLE + " " + PREFIX_CATEGORY, MESSAGE_EMPTY_TITLE_KEYPHRASE);
    }

    @Test
    public void parse_hasDuplicateArgs_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // duplicate amount search parameters
        assertParseFailure(parser, AMOUNT_DESC_BUBBLE_TEA + AMOUNT_DESC_INTERNSHIP,
                MESSAGE_AMOUNT_DUPLICATE_CONSTRAINTS);
        assertParseFailure(parser, AMOUNT_FROM_DESC_BUBBLE_TEA + AMOUNT_FROM_DESC_INTERNSHIP,
                MESSAGE_AMOUNT_FROM_CONSTRAINTS);
        assertParseFailure(parser, AMOUNT_TO_DESC_BUBBLE_TEA + AMOUNT_TO_DESC_INTERNSHIP,
                MESSAGE_AMOUNT_TO_CONSTRAINTS);

        // duplicate date search parameters
        assertParseFailure(parser, DATE_DESC_BUBBLE_TEA + DATE_DESC_INTERNSHIP,
                MESSAGE_DATE_DUPLICATE_CONSTRAINTS);
        assertParseFailure(parser, DATE_FROM_DESC_BUBBLE_TEA + DATE_FROM_DESC_INTERNSHIP,
                MESSAGE_DATE_FROM_CONSTRAINTS);
        assertParseFailure(parser, DATE_TO_DESC_BUBBLE_TEA + DATE_TO_DESC_INTERNSHIP,
                MESSAGE_DATE_TO_CONSTRAINTS);
    }

    @Test
    public void parse_invalidRangeSearch_failure() {
        // amount cannot be used with amount range
        assertParseFailure(parser, AMOUNT_DESC_BUBBLE_TEA + AMOUNT_FROM_DESC_INTERNSHIP,
                MESSAGE_AMOUNT_SEARCH_CONSTRAINTS);
        assertParseFailure(parser, AMOUNT_DESC_BUBBLE_TEA + AMOUNT_TO_DESC_INTERNSHIP,
                MESSAGE_AMOUNT_SEARCH_CONSTRAINTS);

        // invalid amount range - VALID_AMOUNT_BUBBLE_TEA is less than VALID_AMOUNT_INTERNSHIP
        assertParseFailure(parser, AMOUNT_FROM_DESC_INTERNSHIP + AMOUNT_TO_DESC_BUBBLE_TEA,
                MESSAGE_AMOUNT_RANGE_CONSTRAINTS);

        // date cannot be used with date range
        assertParseFailure(parser, DATE_DESC_BUBBLE_TEA + DATE_FROM_DESC_INTERNSHIP,
                MESSAGE_DATE_SEARCH_CONSTRAINTS);
        assertParseFailure(parser, DATE_DESC_BUBBLE_TEA + DATE_TO_DESC_INTERNSHIP,
                MESSAGE_DATE_SEARCH_CONSTRAINTS);

        // invalid date range - VALID_DATE_INTERNSHIP is earlier than VALID_DATE_BUBBLE_TEA
        assertParseFailure(parser, DATE_FROM_DESC_BUBBLE_TEA + DATE_TO_DESC_INTERNSHIP,
                MESSAGE_DATE_RANGE_CONSTRAINTS);

    }

}
