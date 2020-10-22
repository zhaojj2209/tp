package ay2021s1_cs2103_w16_3.finesse.logic.commands.budget;

import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.Command;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandResult;
import ay2021s1_cs2103_w16_3.finesse.model.Model;

/**
 * Adds an expense to the finance tracker.
 */
public class SetExpenseLimitCommand extends Command {

    public static final String COMMAND_WORD = "set-expense-limit";
    public static final String COMMAND_ALIAS = "setel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the monthly expense limit. "
            + "Parameters: AMOUNT\n"
            + "Example: " + COMMAND_WORD + " 500";

    public static final String MESSAGE_SUCCESS = "New monthly expense limit set: %1$s";

    private final String amount;

    /**
     * Creates an AddExpenseCommand to add the specified {@code Expense}.
     */
    public SetExpenseLimitCommand(String amount) {
        requireNonNull(amount);
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.setExpenseLimit(amount);
        return new CommandResult(String.format(MESSAGE_SUCCESS, amount));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetExpenseLimitCommand // instanceof handles nulls
                && amount.equals(((SetExpenseLimitCommand) other).amount));
    }
}
