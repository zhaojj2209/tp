package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense.PREDICATE_SHOW_ALL_EXPENSES;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListExpenseCommand.
 */
public class ListExpenseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
    }

    @Test
    public void execute_showsExpenseOnly() {
        expectedModel.updateFilteredTransactionList(PREDICATE_SHOW_ALL_EXPENSES);
        assertCommandSuccess(new ListExpenseCommand(), model,
                new CommandResult(ListExpenseCommand.MESSAGE_SUCCESS, Tab.EXPENSES), expectedModel);
    }

    @Test
    public void execute_hasSomeExpense() {
        model.addTransaction(new TransactionBuilder().buildExpense());
        expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
        expectedModel.updateFilteredTransactionList(transaction -> transaction instanceof Expense);
        assertCommandSuccess(new ListExpenseCommand(), model,
                new CommandResult(ListExpenseCommand.MESSAGE_SUCCESS, Tab.EXPENSES), expectedModel);
        assertEquals(model.getFilteredTransactionList().size(), 1);
    }

    @Test
    public void execute_hasSomeNonExpense() {
        model.addTransaction(new TransactionBuilder().buildIncome());
        expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
        expectedModel.updateFilteredTransactionList(transaction -> transaction instanceof Expense);
        assertCommandSuccess(new ListExpenseCommand(), model,
                new CommandResult(ListExpenseCommand.MESSAGE_SUCCESS, Tab.EXPENSES), expectedModel);
        assertEquals(model.getFilteredTransactionList().size(), 0);
    }

}
