package ay2021s1_cs2103_w16_3.finesse.model.budget;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * Represents the monthly savings goal set in the finance tracker.
 */
public class MonthlySavingsGoal extends ObservableAmount {

    /**
     * Constructs a {@code MonthlySavingsGoal}.
     *
     * @param goal A valid goal.
     */
    public MonthlySavingsGoal(Amount goal) {
        super(goal);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlySavingsGoal // instanceof handles nulls
                && (getAmount().equals(((MonthlySavingsGoal) other).getAmount()))); // state check
    }

}
