package ay2021s1_cs2103_w16_3.finesse.logic.commands.budget;

import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.Command;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandResult;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Sets the monthly savings goal in the finance tracker.
 */
public class SetSavingsGoalCommand extends Command {

    public static final String COMMAND_WORD = "set-savings-goal";
    public static final String COMMAND_ALIAS = "setsg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the monthly savings goal. "
            + "Parameters: " + PREFIX_AMOUNT + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_AMOUNT + "500";

    public static final String MESSAGE_SUCCESS = "New monthly savings goal set: %1$s";

    private final Amount amount;

    /**
     * Creates a SetSavingsGoalCommand to set the monthly savings goal.
     */
    public SetSavingsGoalCommand(Amount amount) {
        requireNonNull(amount);
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.setSavingsGoal(amount);
        return new CommandResult(String.format(MESSAGE_SUCCESS, amount), true, Tab.OVERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetSavingsGoalCommand // instanceof handles nulls
                && amount.equals(((SetSavingsGoalCommand) other).amount));
    }
}
