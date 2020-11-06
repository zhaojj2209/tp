package ay2021s1_cs2103_w16_3.finesse.logic.commands.budget;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandResult;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

public class SetSavingsGoalCommandTest {

    private Model model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());

    @Test
    public void constructor_nullAmount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetSavingsGoalCommand(null));
    }

    @Test
    public void execute_validAmount_success() {
        Amount amountToSet = new Amount("500");

        CommandResult commandResult = new SetSavingsGoalCommand(amountToSet).execute(model);

        assertEquals(String.format(SetSavingsGoalCommand.MESSAGE_SUCCESS, amountToSet),
                commandResult.getFeedbackToUser());
        assertEquals(amountToSet, model.getMonthlyBudget().getMonthlySavingsGoal().getValue());
        assertEquals(commandResult.getTabToSwitchTo().get(), Tab.OVERVIEW);
    }
}
