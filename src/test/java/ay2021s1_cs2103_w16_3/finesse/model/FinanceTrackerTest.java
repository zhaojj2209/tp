package ay2021s1_cs2103_w16_3.finesse.model;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.ALICE;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.exceptions.DuplicateTransactionException;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinanceTrackerTest {

    private final FinanceTracker financeTracker = new FinanceTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), financeTracker.getTransactionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financeTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFinanceTracker_replacesData() {
        FinanceTracker newData = getTypicalFinanceTracker();
        financeTracker.resetData(newData);
        assertEquals(newData, financeTracker);
    }

    @Test
    public void resetData_withDuplicateTransactions_throwsDuplicateTransactionException() {
        // Two transactions with the same identity fields
        Transaction editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).build();
        List<Transaction> newTransactions = Arrays.asList(ALICE, editedAlice);
        FinanceTrackerStub newData = new FinanceTrackerStub(newTransactions);

        assertThrows(DuplicateTransactionException.class, () -> financeTracker.resetData(newData));
    }

    @Test
    public void hasTransaction_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financeTracker.hasTransaction(null));
    }

    @Test
    public void hasTransacion_transactionNotInFinanceTracker_returnsFalse() {
        assertFalse(financeTracker.hasTransaction(ALICE));
    }

    @Test
    public void hasTransaction_transactionInFinanceTracker_returnsTrue() {
        financeTracker.addTransaction(ALICE);
        assertTrue(financeTracker.hasTransaction(ALICE));
    }

    @Test
    public void hasTransaction_transactionWithSameIdentityFieldsInFinanceTracker_returnsTrue() {
        financeTracker.addTransaction(ALICE);
        Transaction editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(financeTracker.hasTransaction(editedAlice));
    }

    @Test
    public void getTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> financeTracker.getTransactionList().remove(0));
    }

    /**
     * A stub ReadOnlyFinanceTracker whose transactions list can violate interface constraints.
     */
    private static class FinanceTrackerStub implements ReadOnlyFinanceTracker {
        private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        FinanceTrackerStub(Collection<Transaction> transactions) {
            this.transactions.setAll(transactions);
        }

        @Override
        public ObservableList<Transaction> getTransactionList() {
            return transactions;
        }
    }

}
