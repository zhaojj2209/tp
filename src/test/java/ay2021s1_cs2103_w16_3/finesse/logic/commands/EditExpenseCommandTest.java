package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalIndexes.INDEX_SECOND_TRANSACTION;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.core.Messages;
import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.EditCommand.EditTransactionDescriptor;
import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.testutil.EditTransactionDescriptorBuilder;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests
 * for EditExpenseCommand.
 */
public class EditExpenseCommandTest {

    private Model model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Expense editedExpense = new TransactionBuilder().buildExpense();
        EditTransactionDescriptor descriptor =
                new EditTransactionDescriptorBuilder(editedExpense).build();
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, descriptor));

        String expectedMessage = String.format(EditExpenseCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setExpense(model.getFilteredExpenseList().get(0), editedExpense);

        assertCommandSuccess(editExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExpense = Index.fromOneBased(model.getFilteredExpenseList().size());
        Expense lastExpense = model.getFilteredExpenseList().get(indexLastExpense.getZeroBased());

        TransactionBuilder expenseInList = new TransactionBuilder(lastExpense);
        Expense editedExpense = expenseInList.withTitle(VALID_TITLE_BOB).withAmount(VALID_AMOUNT_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).buildExpense();

        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withTitle(VALID_TITLE_BOB).withAmount(VALID_AMOUNT_BOB).withCategories(VALID_CATEGORY_HUSBAND).build();
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(
                new EditCommand(indexLastExpense, descriptor));

        String expectedMessage = String.format(EditExpenseCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setExpense(lastExpense, editedExpense);

        assertCommandSuccess(editExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, new EditTransactionDescriptor()));
        Expense editedExpense = model.getFilteredExpenseList().get(INDEX_FIRST_TRANSACTION.getZeroBased());

        String expectedMessage = String.format(EditExpenseCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());

        assertCommandSuccess(editExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExpenseAtIndex(model, INDEX_FIRST_TRANSACTION);

        Expense expenseInFilteredList = model.getFilteredExpenseList()
                .get(INDEX_FIRST_TRANSACTION.getZeroBased());
        Expense editedExpense =
                new TransactionBuilder(expenseInFilteredList).withTitle(VALID_TITLE_BOB).buildExpense();
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(new EditCommand(INDEX_FIRST_TRANSACTION,
                new EditTransactionDescriptorBuilder().withTitle(VALID_TITLE_BOB).build()));

        String expectedMessage = String.format(EditExpenseCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setExpense(model.getFilteredExpenseList().get(0), editedExpense);

        assertCommandSuccess(editExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidExpenseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withTitle(VALID_TITLE_BOB).build();
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(
                new EditCommand(outOfBoundIndex, descriptor));

        assertCommandFailure(editExpenseCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of finance tracker
     */
    @Test
    public void execute_invalidExpenseIndexFilteredList_failure() {
        showExpenseAtIndex(model, INDEX_FIRST_TRANSACTION);
        Index outOfBoundIndex = INDEX_SECOND_TRANSACTION;
        // ensures that outOfBoundIndex is still in bounds of finance tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFinanceTracker().getExpenseList().size());

        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(new EditCommand(outOfBoundIndex,
                new EditTransactionDescriptorBuilder().withTitle(VALID_TITLE_BOB).build()));

        assertCommandFailure(editExpenseCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditExpenseCommand standardCommand = new EditExpenseCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, DESC_AMY));

        // same values -> returns true
        EditTransactionDescriptor copyDescriptor = new EditTransactionDescriptor(DESC_AMY);
        EditExpenseCommand commandWithSameValues = new EditExpenseCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, copyDescriptor));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TRANSACTION, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TRANSACTION, DESC_BOB)));
    }

}
