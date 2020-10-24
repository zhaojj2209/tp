package ay2021s1_cs2103_w16_3.finesse.model.budget;

import java.math.BigDecimal;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * Represents the remaining budget left in the finance tracker.
 */
public class RemainingBudget extends ObservableAmount {

    /**
     * Constructs a {@code RemainingBudget} with an amount of $0.
     */
    public RemainingBudget() {
        super();
    }

    /**
     * Constructs a {@code RemainingBudget} with the specified budget.
     *
     * @param budget A valid budget.
     */
    public RemainingBudget(Amount budget) {
        super(budget);
    }

    /**
     * Deducts the given amount from the remaining budget.
     * Minimum remaining budget is capped at 0.
     *
     * @param amount A valid amount.
     */
    public void deduct(Amount amount) {
        BigDecimal budget = getAmount().getValue();
        BigDecimal toDeduct = amount.getValue();
        BigDecimal result = budget.subtract(toDeduct);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            setValue(new Amount("0"));
        } else {
            setValue(new Amount(result.toString()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemainingBudget // instanceof handles nulls
                && (getAmount().equals(((RemainingBudget) other).getAmount()))); // state check
    }

}
