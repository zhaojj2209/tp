package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());
    }

    @Test
    public void execute_newTransaction_success() {
        Transaction validTransaction = new TransactionBuilder().build();

        Model expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
        expectedModel.addTransaction(validTransaction);

        assertCommandSuccess(new AddCommand(validTransaction), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTransaction), expectedModel);
    }

    @Test
    public void execute_duplicateTransaction_throwsCommandException() {
        Transaction transactionInList = model.getFinanceTracker().getTransactionList().get(0);
        assertCommandFailure(new AddCommand(transactionInList), model, AddCommand.MESSAGE_DUPLICATE_TRANSACTION);
    }

}
