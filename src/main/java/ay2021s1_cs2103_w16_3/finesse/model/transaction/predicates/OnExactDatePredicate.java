package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Date} matches the date given.
 */
public class OnExactDatePredicate implements Predicate<Transaction> {
    private final Date date;

    public OnExactDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Transaction transaction) {
        return date.equals(transaction.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OnExactDatePredicate // instanceof handles nulls
                && date.equals(((OnExactDatePredicate) other).date)); // state check
    }

}
