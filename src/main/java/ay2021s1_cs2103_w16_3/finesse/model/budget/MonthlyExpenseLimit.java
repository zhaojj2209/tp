package ay2021s1_cs2103_w16_3.finesse.model.budget;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * Represents the monthly expense limit set in the finance tracker.
 * Guarantees: immutable; is a valid ObservableCurrency
 */
public class MonthlyExpenseLimit extends ObservableCurrency {

    /**
     * Constructs a {@code MonthlyExpenseLimit}.
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
                && (getValue().compareTo(((MonthlyExpenseLimit) other).getValue())) == 0); // state check
    }

}
