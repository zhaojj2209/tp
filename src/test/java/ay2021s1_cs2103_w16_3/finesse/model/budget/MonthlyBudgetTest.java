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
    private static final int NUM_OF_MONTHS = 12;

    private final MonthlyBudget monthlyBudget = new MonthlyBudget();
    private final Amount amountToSet = new Amount("500");
    private final CalculatedAmount calculatedAmountToSet = new CalculatedAmount(amountToSet);

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
    public void setMonthlyExpenseLimit_validAmount_successful() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setMonthlyExpenseLimit(amountToSet);
        assertEquals(amountToSet, monthlyBudget.getMonthlyExpenseLimit().getValue());
    }

    @Test
    public void setMonthlySavingsGoal_validAmount_successful() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setMonthlySavingsGoal(amountToSet);
        assertEquals(amountToSet, monthlyBudget.getMonthlySavingsGoal().getValue());
    }

    @Test
    public void setRemainingBudget_validCalculatedAmount_successful() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setRemainingBudget(calculatedAmountToSet);
        assertEquals(calculatedAmountToSet, monthlyBudget.getRemainingBudget().getValue());
    }

    @Test
    public void setCurrentSavings_validCalculatedAmount_successful() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setCurrentSavings(calculatedAmountToSet);
        assertEquals(calculatedAmountToSet, monthlyBudget.getCurrentSavings().getValue());
    }

    @Test
    public void calculateBudgetInfo_calculatesCorrectValues_returnsFalse() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();

        monthlyBudget.setMonthlyExpenseLimit(amountToSet);
        monthlyBudget.setMonthlySavingsGoal(amountToSet);

        FinanceTracker financeTracker = new FinanceTracker();
        financeTracker.setMonthlyBudget(monthlyBudget);

        financeTracker.calculateBudgetInfo();

        assertEquals(calculatedAmountToSet, financeTracker.getMonthlyBudget().getRemainingBudget().getValue());
        assertEquals(new CalculatedAmount(), financeTracker.getMonthlyBudget().getCurrentSavings().getValue());

    }

    @Test
    public void equals_distinctMonthlyBudgetsWithSameAttributes_returnsTrue() {
        MonthlyBudget firstMonthlyBudget = new MonthlyBudget();
        MonthlyBudget secondMonthlyBudget = new MonthlyBudget();

        firstMonthlyBudget.setMonthlyExpenseLimit(amountToSet);
        secondMonthlyBudget.setMonthlyExpenseLimit(amountToSet);
        firstMonthlyBudget.setMonthlySavingsGoal(amountToSet);
        secondMonthlyBudget.setMonthlySavingsGoal(amountToSet);

        assertNotSame(firstMonthlyBudget, secondMonthlyBudget);
        assertEquals(firstMonthlyBudget, secondMonthlyBudget);
    }

    @Test
    public void equals_sameSetValuesDifferentCalculatedValues_returnsFalse() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();

        monthlyBudget.setMonthlyExpenseLimit(amountToSet);
        monthlyBudget.setMonthlySavingsGoal(amountToSet);

        FinanceTracker financeTracker = new FinanceTracker();
        financeTracker.setTransactions(getTypicalTransactions());
        financeTracker.setMonthlyBudget(monthlyBudget);

        // ensure monthly budgets were equal before calculating budget info
        assertEquals(financeTracker.getMonthlyBudget(), monthlyBudget);

        financeTracker.calculateBudgetInfo();

        // monthly budgets are now not equal
        assertNotEquals(financeTracker.getMonthlyBudget(), monthlyBudget);
    }

}
