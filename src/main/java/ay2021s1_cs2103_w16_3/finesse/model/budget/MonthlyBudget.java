package ay2021s1_cs2103_w16_3.finesse.model.budget;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.TransactionList;

/**
 * Represents the monthly budget in the finance tracker.
 */
public class MonthlyBudget {
    private MonthlyExpenseLimit monthlyExpenseLimit;
    private MonthlySavingsGoal monthlySavingsGoal;
    private RemainingBudget remainingBudget;

    /**
     * Creates a {@code MonthlyBudget} with an expense limit and savings goal of $0.
     */
    public MonthlyBudget() {
        monthlyExpenseLimit = new MonthlyExpenseLimit();
        monthlySavingsGoal = new MonthlySavingsGoal();
        remainingBudget = new RemainingBudget();
    }

    public MonthlyExpenseLimit getMonthlyExpenseLimit() {
        return monthlyExpenseLimit;
    }

    public void setMonthlyExpenseLimit(Amount limit) {
        monthlyExpenseLimit.setValue(limit);
    }

    public MonthlySavingsGoal getMonthlySavingsGoal() {
        return monthlySavingsGoal;
    }

    public void setMonthlySavingsGoal(Amount goal) {
        monthlySavingsGoal.setValue(goal);
    }

    public RemainingBudget getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Amount budget) {
        remainingBudget.setValue(budget);
    }

    /**
     * Calculates the user's remaining budget for the month.
     * Steps:
     * 1. Set the remaining budget to the user's monthly expense limit.
     * 2. For each expense in the finance tracker, deduct its amount from the remaining budget.
     * 3. The minimum remaining budget is capped at 0.
     */
    public void calculateRemainingBudget(TransactionList transactions) {
        setRemainingBudget(monthlyExpenseLimit.getAmount());
        for (Transaction transaction: transactions) {
            if (transaction instanceof Expense) {
                remainingBudget.deduct(transaction.getAmount());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlyBudget // instanceof handles nulls
                && monthlyExpenseLimit.equals(((MonthlyBudget) other).monthlyExpenseLimit));
    }
}
