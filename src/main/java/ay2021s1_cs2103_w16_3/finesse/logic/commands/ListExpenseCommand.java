package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense.PREDICATE_SHOW_ALL_EXPENSES;
import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.model.Model;

/**
 * Lists all expenses in the finance tracker to the user.
 */
public class ListExpenseCommand extends Command {

    public static final String COMMAND_WORD = "ls-expense";
    public static final String COMMAND_ALIAS = "lse";

    public static final String MESSAGE_SUCCESS = "Listed all expenses";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_EXPENSES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
