package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.showIncomeAtIndex;
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
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.testutil.EditTransactionDescriptorBuilder;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests
 * for EditIncomeCommand.
 */
public class EditIncomeCommandTest {

    private Model model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Income editedIncome = new TransactionBuilder().buildIncome();
        EditTransactionDescriptor descriptor =
                new EditTransactionDescriptorBuilder(editedIncome).build();
        EditIncomeCommand editIncomeCommand = new EditIncomeCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, descriptor));

        String expectedMessage = String.format(EditIncomeCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedIncome);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setIncome(model.getFilteredIncomeList().get(0), editedIncome);

        assertCommandSuccess(editIncomeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastIncome = Index.fromOneBased(model.getFilteredIncomeList().size());
        Income lastIncome = model.getFilteredIncomeList().get(indexLastIncome.getZeroBased());

        TransactionBuilder incomeInList = new TransactionBuilder(lastIncome);
        Income editedIncome = incomeInList.withTitle(VALID_TITLE_BOB).withAmount(VALID_AMOUNT_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).buildIncome();

        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withTitle(VALID_TITLE_BOB).withAmount(VALID_AMOUNT_BOB).withCategories(VALID_CATEGORY_HUSBAND).build();
        EditIncomeCommand editIncomeCommand = new EditIncomeCommand(
                new EditCommand(indexLastIncome, descriptor));

        String expectedMessage = String.format(EditIncomeCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedIncome);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setIncome(lastIncome, editedIncome);

        assertCommandSuccess(editIncomeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditIncomeCommand editIncomeCommand = new EditIncomeCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, new EditTransactionDescriptor()));
        Income editedIncome = model.getFilteredIncomeList().get(INDEX_FIRST_TRANSACTION.getZeroBased());

        String expectedMessage = String.format(EditIncomeCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedIncome);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());

        assertCommandSuccess(editIncomeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showIncomeAtIndex(model, INDEX_FIRST_TRANSACTION);

        Income incomeInFilteredList = model.getFilteredIncomeList()
                .get(INDEX_FIRST_TRANSACTION.getZeroBased());
        Income editedIncome =
                new TransactionBuilder(incomeInFilteredList).withTitle(VALID_TITLE_BOB).buildIncome();
        EditIncomeCommand editIncomeCommand = new EditIncomeCommand(new EditCommand(INDEX_FIRST_TRANSACTION,
                new EditTransactionDescriptorBuilder().withTitle(VALID_TITLE_BOB).build()));

        String expectedMessage = String.format(EditIncomeCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedIncome);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setIncome(model.getFilteredIncomeList().get(0), editedIncome);

        assertCommandSuccess(editIncomeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIncomeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIncomeList().size() + 1);
        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withTitle(VALID_TITLE_BOB).build();
        EditIncomeCommand editIncomeCommand = new EditIncomeCommand(
                new EditCommand(outOfBoundIndex, descriptor));

        assertCommandFailure(editIncomeCommand, model, Messages.MESSAGE_INVALID_INCOME_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of finance tracker
     */
    @Test
    public void execute_invalidIncomeIndexFilteredList_failure() {
        showIncomeAtIndex(model, INDEX_FIRST_TRANSACTION);
        Index outOfBoundIndex = INDEX_SECOND_TRANSACTION;
        // ensures that outOfBoundIndex is still in bounds of finance tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFinanceTracker().getIncomeList().size());

        EditIncomeCommand editIncomeCommand = new EditIncomeCommand(new EditCommand(outOfBoundIndex,
                new EditTransactionDescriptorBuilder().withTitle(VALID_TITLE_BOB).build()));

        assertCommandFailure(editIncomeCommand, model, Messages.MESSAGE_INVALID_INCOME_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditIncomeCommand standardCommand = new EditIncomeCommand(
                new EditCommand(INDEX_FIRST_TRANSACTION, DESC_AMY));

        // same values -> returns true
        EditTransactionDescriptor copyDescriptor = new EditTransactionDescriptor(DESC_AMY);
        EditIncomeCommand commandWithSameValues = new EditIncomeCommand(
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
