package ay2021s1_cs2103_w16_3.finesse.model.budget;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * Represents the monthly expense limit set in the finance tracker.
 */
public class MonthlyExpenseLimit extends ObservableAmount {

    /**
     * Constructs a {@code MonthlyExpenseLimit} with an amount of $0.
     */
    public MonthlyExpenseLimit() {
        super();
    }

    /**
     * Constructs a {@code MonthlyExpenseLimit} with the specified limit.
     *
     * @param limit A valid limit.
     */
    public MonthlyExpenseLimit(Amount limit) {
        super(limit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlyExpenseLimit // instanceof handles nulls
                && (getAmount().equals(((MonthlyExpenseLimit) other).getAmount()))); // state check
    }

}
