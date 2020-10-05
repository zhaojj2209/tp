package ay2021s1_cs2103_w16_3.finesse.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.UniqueTransactionList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the finance tracker level
 * Duplicates are not allowed (by .isSameTransaction comparison)
 */
public class FinanceTracker implements ReadOnlyFinanceTracker {

    private final UniqueTransactionList transactions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        transactions = new UniqueTransactionList();
    }

    public FinanceTracker() {}

    /**
     * Creates an FinanceTracker using the Transactions in the {@code toBeCopied}
     */
    public FinanceTracker(ReadOnlyFinanceTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Resets the existing data of this {@code FinanceTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyFinanceTracker newData) {
        requireNonNull(newData);

        setTransactions(newData.getTransactionList());
    }

    //// transaction-level operations

    /**
     * Returns true if a transaction with the same identity as {@code transaction} exists in the finance tracker.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    /**
     * Adds a transaction to the finance tracker.
     * The transaction must not already exist in the finance tracker.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Replaces the given transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the finance tracker.
     * The transaction identity of {@code editedTransaction} must not be the same as another existing transaction
     * in the finance tracker.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);

        transactions.setTransaction(target, editedTransaction);
    }

    /**
     * Removes {@code key} from this {@code FinanceTracker}.
     * {@code key} must exist in the finance tracker.
     */
    public void removeTransaction(Transaction key) {
        transactions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return transactions.asUnmodifiableObservableList().size() + " transactions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinanceTracker // instanceof handles nulls
                && transactions.equals(((FinanceTracker) other).transactions));
    }

    @Override
    public int hashCode() {
        return transactions.hashCode();
    }
}
