package ay2021s1_cs2103_w16_3.finesse.logic.parser;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT_FROM;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT_TO;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE_TO;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.FindCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.exceptions.ParseException;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.HasCategoriesPredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.HasExactAmountPredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.InAmountRangePredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.InDateRangePredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.OnExactDatePredicate;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AMOUNT, PREFIX_DATE, PREFIX_CATEGORY,
                        PREFIX_AMOUNT_FROM, PREFIX_AMOUNT_TO, PREFIX_DATE_FROM, PREFIX_DATE_TO);
        List<Predicate<Transaction>> predicateList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            predicateList.add(new TitleContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_TITLE)));
        }

        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
            predicateList.add(new HasExactAmountPredicate(amount));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            predicateList.add(new OnExactDatePredicate(date));
        }

        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            predicateList.add(new HasCategoriesPredicate(argMultimap.getAllValues(PREFIX_CATEGORY)));
        }

        if (argMultimap.getValue(PREFIX_AMOUNT_FROM).isPresent()
                || argMultimap.getValue(PREFIX_AMOUNT_TO).isPresent()) {
            Amount amountFrom = null;
            Amount amountTo = null;
            if (argMultimap.getValue(PREFIX_AMOUNT_FROM).isPresent()) {
                amountFrom = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT_FROM).get());
            }
            if (argMultimap.getValue(PREFIX_AMOUNT_TO).isPresent()) {
                amountTo = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT_TO).get());
            }
            predicateList.add(new InAmountRangePredicate(Optional.ofNullable(amountFrom),
                    Optional.ofNullable(amountTo)));
        }

        if (argMultimap.getValue(PREFIX_DATE_FROM).isPresent()
                || argMultimap.getValue(PREFIX_DATE_TO).isPresent()) {
            Date dateFrom = null;
            Date dateTo = null;
            if (argMultimap.getValue(PREFIX_DATE_FROM).isPresent()) {
                dateFrom = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_FROM).get());
            }
            if (argMultimap.getValue(PREFIX_DATE_TO).isPresent()) {
                dateTo = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_TO).get());
            }
            predicateList.add(new InDateRangePredicate(Optional.ofNullable(dateFrom),
                    Optional.ofNullable(dateTo)));
        }

        if (!argMultimap.getPreamble().isEmpty() || predicateList.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(predicateList);
    }

}
