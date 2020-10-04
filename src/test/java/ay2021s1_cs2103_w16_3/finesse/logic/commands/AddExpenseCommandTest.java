package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.exceptions.CommandException;
import ay2021s1_cs2103_w16_3.finesse.model.AddressBook;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyAddressBook;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyUserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;
import javafx.collections.ObservableList;

public class AddExpenseCommandTest {

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExpenseCommand(null));
    }

    @Test
    public void execute_transactionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTransactionAdded modelStub = new ModelStubAcceptingTransactionAdded();
        Expense validExpense = new TransactionBuilder().buildExpense();

        CommandResult commandResult = new AddExpenseCommand(validExpense).execute(modelStub);

        assertEquals(String.format(AddExpenseCommand.MESSAGE_SUCCESS, validExpense), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExpense), modelStub.transactionsAdded);
    }

    @Test
    public void execute_duplicateTransaction_throwsCommandException() {
        Expense validExpense = new TransactionBuilder().buildExpense();
        AddExpenseCommand addExpenseCommand = new AddExpenseCommand(validExpense);
        ModelStub modelStub = new ModelStubWithTransaction(validExpense);

        assertThrows(CommandException.class, AddExpenseCommand.MESSAGE_DUPLICATE_TRANSACTION, ()
            -> addExpenseCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Expense alice = new TransactionBuilder().withName("Alice").buildExpense();
        Expense bob = new TransactionBuilder().withName("Bob").buildExpense();
        AddExpenseCommand addAliceCommand = new AddExpenseCommand(alice);
        AddExpenseCommand addBobCommand = new AddExpenseCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddExpenseCommand addAliceCommandCopy = new AddExpenseCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different transaction -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTransaction(Transaction transaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTransaction(Transaction transaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTransaction(Transaction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransaction(Transaction target, Transaction editedTransaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Transaction> getFilteredTransactionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single transaction.
     */
    private class ModelStubWithTransaction extends ModelStub {
        private final Transaction transaction;

        ModelStubWithTransaction(Transaction transaction) {
            requireNonNull(transaction);
            this.transaction = transaction;
        }

        @Override
        public boolean hasTransaction(Transaction transaction) {
            requireNonNull(transaction);
            return this.transaction.isSameTransaction(transaction);
        }
    }

    /**
     * A Model stub that always accept the transaction being added.
     */
    private class ModelStubAcceptingTransactionAdded extends ModelStub {
        final ArrayList<Transaction> transactionsAdded = new ArrayList<>();

        @Override
        public boolean hasTransaction(Transaction transaction) {
            requireNonNull(transaction);
            return transactionsAdded.stream().anyMatch(transaction::isSameTransaction);
        }

        @Override
        public void addTransaction(Transaction transaction) {
            requireNonNull(transaction);
            transactionsAdded.add(transaction);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
