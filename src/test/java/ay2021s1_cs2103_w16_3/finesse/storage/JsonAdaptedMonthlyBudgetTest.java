package ay2021s1_cs2103_w16_3.finesse.storage;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.testutil.MonthlyBudgetBuilder;

public class JsonAdaptedMonthlyBudgetTest {
    private static final String INVALID_AMOUNT = "+651234";
    private static final String VALID_AMOUNT = "500";

    @Test
    public void toModelType_validValues_returnsMonthlyBudget() throws Exception {
        MonthlyBudget validExpenseLimitBudget = new MonthlyBudgetBuilder().buildMonthlyBudget();
        JsonAdaptedMonthlyBudget monthlyBudget = new JsonAdaptedMonthlyBudget(validExpenseLimitBudget);
        assertEquals(validExpenseLimitBudget, monthlyBudget.toModelType());
    }

    @Test
    public void toModelType_invalidMonthlyExpenseLimit_throwsIllegalValueException() {
        JsonAdaptedMonthlyBudget monthlyBudget = new JsonAdaptedMonthlyBudget(INVALID_AMOUNT, VALID_AMOUNT);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, monthlyBudget::toModelType);
    }

    @Test
    public void toModelType_invalidMonthlySavingsGoal_throwsIllegalValueException() {
        JsonAdaptedMonthlyBudget monthlyBudget = new JsonAdaptedMonthlyBudget(VALID_AMOUNT, INVALID_AMOUNT);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, monthlyBudget::toModelType);
    }

}
