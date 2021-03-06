package ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmarkcommands;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_BOOKMARK_EXPENSE_DISPLAYED_INDEX;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_PHONE_BILL;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_SPOTIFY_SUBSCRIPTION;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_SPOTIFY_SUBSCRIPTION;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_MISCELLANEOUS;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BUBBLE_TEA;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_SPOTIFY_SUBSCRIPTION;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.showBookmarkExpenseAtIndex;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark.EditBookmarkExpenseCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark.EditBookmarkTransactionDescriptor;
import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.bookmark.BookmarkExpense;
import ay2021s1_cs2103_w16_3.finesse.testutil.BookmarkTransactionBuilder;
import ay2021s1_cs2103_w16_3.finesse.testutil.EditBookmarkTransactionDescriptorBuilder;

public class EditBookmarkExpenseCommandTest {

    private Model model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        BookmarkExpense editedBookmarkExpense = new BookmarkTransactionBuilder()
                .withTitle(VALID_TITLE_BUBBLE_TEA).withAmount(VALID_AMOUNT_SPOTIFY_SUBSCRIPTION)
                .buildBookmarkExpense();
        EditBookmarkTransactionDescriptor descriptor =
                new EditBookmarkTransactionDescriptorBuilder(editedBookmarkExpense).build();
        EditBookmarkExpenseCommand editBookmarkExpenseCommand =
                new EditBookmarkExpenseCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditBookmarkExpenseCommand.MESSAGE_EDIT_BOOKMARK_EXPENSE_SUCCESS,
                editedBookmarkExpense);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setBookmarkExpense(model.getFilteredBookmarkExpenseList().get(0), editedBookmarkExpense);

        assertCommandSuccess(editBookmarkExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExpense = Index.fromOneBased(model.getFilteredBookmarkExpenseList().size());
        BookmarkExpense lastExpense = model.getFilteredBookmarkExpenseList().get(indexLastExpense.getZeroBased());

        BookmarkTransactionBuilder expenseInList = new BookmarkTransactionBuilder(lastExpense);
        BookmarkExpense editedExpense = expenseInList.withTitle(VALID_TITLE_BUBBLE_TEA)
                .withAmount(VALID_AMOUNT_BUBBLE_TEA)
                .withCategories(VALID_CATEGORY_MISCELLANEOUS).buildBookmarkExpense();

        EditBookmarkTransactionDescriptor descriptor = new EditBookmarkTransactionDescriptorBuilder()
                .withTitle(VALID_TITLE_BUBBLE_TEA).withAmount(VALID_AMOUNT_BUBBLE_TEA)
                .withCategories(VALID_CATEGORY_MISCELLANEOUS).build();
        EditBookmarkExpenseCommand editBookmarkExpenseCommand =
                new EditBookmarkExpenseCommand(indexLastExpense, descriptor);

        String expectedMessage = String.format(EditBookmarkExpenseCommand.MESSAGE_EDIT_BOOKMARK_EXPENSE_SUCCESS,
                editedExpense);

        Model expectedModel = new ModelManager(new FinanceTracker(model.getFinanceTracker()), new UserPrefs());
        expectedModel.setBookmarkExpense(lastExpense, editedExpense);

        assertCommandSuccess(editBookmarkExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookmarkExpenseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookmarkExpenseList().size() + 1);
        EditBookmarkTransactionDescriptor descriptor = new EditBookmarkTransactionDescriptorBuilder()
                .withTitle(VALID_TITLE_SPOTIFY_SUBSCRIPTION).build();
        EditBookmarkExpenseCommand editExpenseCommand = new EditBookmarkExpenseCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editExpenseCommand, model, MESSAGE_INVALID_BOOKMARK_EXPENSE_DISPLAYED_INDEX);
    }


    /**
     * Edit filtered bookmark expense list where index is larger than size of filtered bookmark expense list,
     * but smaller than size of finance tracker
     */
    @Test
    public void execute_invalidBookmarkExpenseIndexFilteredList_failure() {
        showBookmarkExpenseAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of bookmark expense list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFinanceTracker().getBookmarkExpenseList().size());

        EditBookmarkExpenseCommand editBookmarkExpenseCommand = new EditBookmarkExpenseCommand(outOfBoundIndex,
                new EditBookmarkTransactionDescriptorBuilder().withTitle(VALID_TITLE_SPOTIFY_SUBSCRIPTION).build());

        assertCommandFailure(editBookmarkExpenseCommand, model, MESSAGE_INVALID_BOOKMARK_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBookmarkExpenseCommand standardCommand =
                new EditBookmarkExpenseCommand(INDEX_FIRST, DESC_SPOTIFY_SUBSCRIPTION);

        // same values -> returns true
        EditBookmarkTransactionDescriptor copyDescriptor =
                new EditBookmarkTransactionDescriptor(DESC_SPOTIFY_SUBSCRIPTION);
        EditBookmarkExpenseCommand commandWithSameValues =
                new EditBookmarkExpenseCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand
                .equals(new EditBookmarkExpenseCommand(INDEX_SECOND, DESC_SPOTIFY_SUBSCRIPTION)));

        // different descriptor -> returns false
        assertFalse(standardCommand
                .equals(new EditBookmarkExpenseCommand(INDEX_FIRST, DESC_PHONE_BILL)));
    }

}
