package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import java.util.Optional;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Amount} is within the given amount range.
 */
public class InAmountRangePredicate implements Predicate<Transaction> {
    private final Optional<Amount> amountFrom;
    private final Optional<Amount> amountTo;


    /**
     * Constructs a {@code InAmountRangePredicate}.
     *
     * @param amountFrom The lower bound of the amount range, if given.
     * @param amountTo The upper bound of the amount range, if given.
     */
    public InAmountRangePredicate(Optional<Amount> amountFrom, Optional<Amount> amountTo) {
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }

    @Override
    public boolean test(Transaction transaction) {
        boolean testResult = true;

        if (amountFrom.isPresent()) {
            testResult = testResult && (amountFrom.get().compareTo(transaction.getAmount()) <= 0);
        }

        if (amountTo.isPresent()) {
            testResult = testResult && amountTo.get().compareTo(transaction.getAmount()) >= 0;
        }

        return testResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InAmountRangePredicate // instanceof handles nulls
                && amountFrom.equals(((InAmountRangePredicate) other).amountFrom)
                && amountTo.equals(((InAmountRangePredicate) other).amountTo)); // state check
    }

}
