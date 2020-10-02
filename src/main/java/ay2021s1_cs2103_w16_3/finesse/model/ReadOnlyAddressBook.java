package ay2021s1_cs2103_w16_3.finesse.model;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the transactions list.
     * This list will not contain any duplicate transactions.
     */
    ObservableList<Transaction> getTransactionList();

}
