package ay2021s1_cs2103_w16_3.finesse.testutil;

import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * A utility class to help with building FinanceTracker objects.
 * Example usage: <br>
 *     {@code FinanceTracker ab = new FinanceTrackerBuilder().withTransaction("John", "Doe").build();}
 */
public class FinanceTrackerBuilder {

    private FinanceTracker financeTracker;

    public FinanceTrackerBuilder() {
        financeTracker = new FinanceTracker();
    }

    public FinanceTrackerBuilder(FinanceTracker financeTracker) {
        this.financeTracker = financeTracker;
    }

    /**
     * Adds a new {@code Transaction} to the {@code FinanceTracker} that we are building.
     */
    public FinanceTrackerBuilder withTransaction(Transaction transaction) {
        financeTracker.addTransaction(transaction);
        return this;
    }

    public FinanceTracker build() {
        return financeTracker;
    }
}