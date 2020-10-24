package ay2021s1_cs2103_w16_3.finesse.logic.commands.budget;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

public class SetExpenseLimitCommandTest {

    private Model model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());

    @Test
    public void constructor_nullAmount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetExpenseLimitCommand(null));
    }

    @Test
    public void execute_validAmount_success() {
        Amount amountToSet = new Amount("500");
        SetExpenseLimitCommand setExpenseLimitCommand = new SetExpenseLimitCommand(amountToSet);

        String expectedMessage = String.format(SetExpenseLimitCommand.MESSAGE_SUCCESS, amountToSet);

        ModelManager expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
        expectedModel.setExpenseLimit(amountToSet);

        assertCommandSuccess(setExpenseLimitCommand, model, expectedMessage, expectedModel);
    }
}
