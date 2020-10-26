package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Amount} matches the amount given.
 */
public class HasExactAmountPredicate implements Predicate<Transaction> {
    private final Amount amount;

    public HasExactAmountPredicate(Amount amount) {
        this.amount = amount;
    }

    @Override
    public boolean test(Transaction transaction) {
        return amount.equals(transaction.getAmount());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasExactAmountPredicate // instanceof handles nulls
                && amount.equals(((HasExactAmountPredicate) other).amount)); // state check
    }

}
