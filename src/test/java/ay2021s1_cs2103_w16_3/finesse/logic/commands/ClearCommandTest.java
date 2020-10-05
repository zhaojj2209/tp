package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFinanceTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFinanceTracker_success() {
        Model model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());
        expectedModel.setFinanceTracker(new FinanceTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
