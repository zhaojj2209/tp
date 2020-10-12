package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;
import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Lists all transactions in the finance tracker to the user.
 */
public class ListTransactionCommand extends Command {

    public static final String COMMAND_WORD = "ls-overview";
    public static final String COMMAND_ALIAS = "lso";

    public static final String MESSAGE_SUCCESS = "Listed all transactions.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        return new CommandResult(MESSAGE_SUCCESS, Tab.OVERVIEW);
    }
}
