package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import java.util.Optional;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Date} is with the given date range.
 */
public class InDateRangePredicate implements Predicate<Transaction> {
    private final Optional<Date> dateFrom;
    private final Optional<Date> dateTo;

    /**
     * Constructs a {@code InDateRangePredicate}.
     *
     * @param dateFrom The lower bound of the date range, if given.
     * @param dateTo The upper bound of the date range, if given.
     */
    public InDateRangePredicate(Optional<Date> dateFrom, Optional<Date> dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean test(Transaction transaction) {
        boolean testResult = true;

        if (dateFrom.isPresent()) {
            testResult = testResult && (dateFrom.get().compareTo(transaction.getDate()) <= 0);
        }

        if (dateTo.isPresent()) {
            testResult = testResult && dateTo.get().compareTo(transaction.getDate()) >= 0;
        }

        return testResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InDateRangePredicate // instanceof handles nulls
                && dateFrom.equals(((InDateRangePredicate) other).dateFrom)
                && dateTo.equals(((InDateRangePredicate) other).dateTo)); // state check
    }

}
