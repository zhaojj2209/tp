package ay2021s1_cs2103_w16_3.finesse.model.budget;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalTransactions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount.CalculatedAmount;

public class MonthlyBudgetTest {
    private final MonthlyBudget monthlyBudget = new MonthlyBudget();

    @Test
    public void constructor() {
        CalculatedAmount initialCalculatedAmount = new CalculatedAmount();
        assertEquals(Amount.ZERO_AMOUNT, monthlyBudget.getMonthlyExpenseLimit().getValue());
        assertEquals(Amount.ZERO_AMOUNT, monthlyBudget.getMonthlySavingsGoal().getValue());
        assertEquals(initialCalculatedAmount, monthlyBudget.getRemainingBudget().getValue());
        assertEquals(initialCalculatedAmount, monthlyBudget.getCurrentSavings().getValue());
        assertEquals(Collections.emptyList(), monthlyBudget.getMonthlyExpenses());
        assertEquals(Collections.emptyList(), monthlyBudget.getMonthlyIncomes());
        assertEquals(Collections.emptyList(), monthlyBudget.getMonthlySavings());
        assertEquals(Collections.emptyList(), monthlyBudget.getMonths());
    }

    @Test
    public void getMonthlyExpenses_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> monthlyBudget.getMonthlyExpenses().remove(0));
    }

    @Test
    public void getMonthlyIncomes_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> monthlyBudget.getMonthlyIncomes().remove(0));
    }

    @Test
    public void getMonthlySavings_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> monthlyBudget.getMonthlySavings().remove(0));
    }

    @Test
    public void getMonths_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> monthlyBudget.getMonths().remove(0));
    }

    @Test
    public void equals_distinctMonthlyBudgetsWithSameAttributes_returnsTrue() {
        MonthlyBudget firstMonthlyBudget = new MonthlyBudget();
        MonthlyBudget secondMonthlyBudget = new MonthlyBudget();

        firstMonthlyBudget.setMonthlyExpenseLimit(new Amount("500"));
        secondMonthlyBudget.setMonthlyExpenseLimit(new Amount("500"));
        firstMonthlyBudget.setMonthlySavingsGoal(new Amount("500"));
        secondMonthlyBudget.setMonthlySavingsGoal(new Amount("500"));

        assertNotSame(firstMonthlyBudget, secondMonthlyBudget);
        assertEquals(firstMonthlyBudget, secondMonthlyBudget);
    }

    @Test
    public void equals_sameSetValuesDifferentCalculatedValues_returnsFalse() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();

        monthlyBudget.setMonthlyExpenseLimit(new Amount("500"));
        monthlyBudget.setMonthlySavingsGoal(new Amount("500"));

        FinanceTracker financeTracker = new FinanceTracker();
        financeTracker.setTransactions(getTypicalTransactions());
        financeTracker.setMonthlyBudget(monthlyBudget);

        assertEquals(financeTracker.getMonthlyBudget(), monthlyBudget);

        financeTracker.calculateBudgetInfo();

        assertNotEquals(financeTracker.getMonthlyBudget(), monthlyBudget);
    }

}
