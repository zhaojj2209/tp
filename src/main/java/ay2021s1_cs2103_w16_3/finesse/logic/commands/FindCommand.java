package ay2021s1_cs2103_w16_3.finesse.logic.commands;


import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT_FROM;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT_TO;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE_TO;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.commons.core.Messages;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Finds and lists all transactions in the finance tracker whose title contains any of the argument keywords
 * depending on the tab the user is on.
 * Keyword matching is case insensitive.
 *
 * Base class for FindExpenseCommand, FindIncomeCommand and FindTransactionCommand.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all transactions on the current tab "
            + "that match any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "When on Overview tab: Searches all transactions.\n"
            + "When on Income tab: Searches all incomes.\n"
            + "When on Expenses tab: Searches all expenses.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Types of keywords:\n"
            + PREFIX_TITLE + "KEYWORD: Searches for all transactions on the current tab"
            + " with titles containing the specified keyword (case-insensitive). "
            + "More than one keyword can be given.\n"
            + PREFIX_AMOUNT + "AMOUNT: Searches for all transactions on the current tab with the specified amount.\n"
            + PREFIX_DATE + "DATE: Searches for all transactions on the current tab "
            + "that occurred on the specified date.\n"
            + PREFIX_CATEGORY + "CATEGORY: Searches for all transactions on the current tab"
            + " labelled with the specified category (case-insensitive). "
            + "More than one keyword can be given.\n"
            + PREFIX_AMOUNT_FROM + "AMOUNT_FROM: Searches for all transactions on the current tab"
            + " with amounts more than or equal to the specified amount.\n"
            + PREFIX_AMOUNT_TO + "AMOUNT_TO: Searches for all transactions on the current tab"
            + " with amounts less than or equal to the specified amount.\n"
            + PREFIX_DATE_FROM + "DATE_FROM: Searches for all transactions on the current tab"
            + " that occurred on or later than the specified date.\n"
            + PREFIX_DATE_TO + "DATE_TO: Searches for all transactions on the current tab"
            + " that occurred on or before the specified date.\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Bubble Tea "
            + PREFIX_AMOUNT_FROM + "5 "
            + PREFIX_DATE_FROM + "01/10/2020 "
            + PREFIX_DATE_TO + "31/10/2020";

    private final List<Predicate<Transaction>> predicates;

    public FindCommand(List<Predicate<Transaction>> predicates) {
        this.predicates = predicates;
    }

    protected List<Predicate<Transaction>> getPredicates() {
        return predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(predicates);
        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                        model.getFilteredTransactionList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
