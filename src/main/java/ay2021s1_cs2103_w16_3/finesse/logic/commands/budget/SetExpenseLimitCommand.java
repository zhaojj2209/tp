package ay2021s1_cs2103_w16_3.finesse.logic.commands.budget;

import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.Command;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandResult;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * Sets the monthly expense limit in the finance tracker.
 */
public class SetExpenseLimitCommand extends Command {

    public static final String COMMAND_WORD = "set-expense-limit";
    public static final String COMMAND_ALIAS = "setel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the monthly expense limit. "
            + "Parameters: " + PREFIX_AMOUNT + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_AMOUNT + "500";

    public static final String MESSAGE_SUCCESS = "New monthly expense limit set: %1$s";

    private final Amount amount;

    /**
     * Creates a SetExpenseLimitCommand to set the monthly expense limit.
     */
    public SetExpenseLimitCommand(Amount amount) {
        requireNonNull(amount);
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.setExpenseLimit(amount);
        return new CommandResult(String.format(MESSAGE_SUCCESS, amount), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetExpenseLimitCommand // instanceof handles nulls
                && amount.equals(((SetExpenseLimitCommand) other).amount));
    }
}
