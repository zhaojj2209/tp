package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.commons.core.Messages;
import ay2021s1_cs2103_w16_3.finesse.model.Model;

/**
 * Finds and lists all expenses in the finance tracker whose title contains any of the argument keywords.
 * Used when the user is on the Expenses tab.
 * Keyword matching is case insensitive.
 */
public class FindExpenseCommand extends FindCommand {

    public FindExpenseCommand(FindCommand superCommand) {
        super(superCommand.getPredicate());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExpenseList(getPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                        model.getFilteredExpenseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindExpenseCommand // instanceof handles nulls
                && getPredicate().equals(((FindExpenseCommand) other).getPredicate())); // state check
    }
}
