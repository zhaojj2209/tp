package ay2021s1_cs2103_w16_3.finesse.model.budget;

import java.math.BigDecimal;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.TransactionList;

/**
 * Represents the monthly budget in the finance tracker.
 */
public class MonthlyBudget {
    private static final Amount ZERO_AMOUNT = new Amount("0");

    private ObservableAmount monthlyExpenseLimit;
    private ObservableAmount monthlySavingsGoal;
    private ObservableAmount remainingBudget;
    private ObservableAmount currentSavings;
    private ObservableAmount budgetDeficit;
    private ObservableAmount savingsDeficit;
    private BigDecimal totalExpenses;
    private BigDecimal totalIncomes;

    /**
     * Creates a {@code MonthlyBudget} with an expense limit and savings goal of $0.
     */
    public MonthlyBudget() {
        monthlyExpenseLimit = new ObservableAmount();
        monthlySavingsGoal = new ObservableAmount();
        remainingBudget = new ObservableAmount();
        currentSavings = new ObservableAmount();
        budgetDeficit = new ObservableAmount();
        savingsDeficit = new ObservableAmount();
        totalExpenses = BigDecimal.ZERO;
        totalIncomes = BigDecimal.ZERO;
    }

    public ObservableAmount getMonthlyExpenseLimit() {
        return monthlyExpenseLimit;
    }

    public void setMonthlyExpenseLimit(Amount limit) {
        monthlyExpenseLimit.setValue(limit);
    }

    public ObservableAmount getMonthlySavingsGoal() {
        return monthlySavingsGoal;
    }

    public void setMonthlySavingsGoal(Amount goal) {
        monthlySavingsGoal.setValue(goal);
    }

    public ObservableAmount getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Amount budget) {
        remainingBudget.setValue(budget);
    }

    public ObservableAmount getCurrentSavings() {
        return currentSavings;
    }

    public void setCurrentSavings(Amount savings) {
        currentSavings.setValue(savings);
    }

    public ObservableAmount getBudgetDeficit() {
        return budgetDeficit;
    }

    public void setBudgetDeficit(Amount deficit) {
        budgetDeficit.setValue(deficit);
    }

    public ObservableAmount getSavingsDeficit() {
        return savingsDeficit;
    }

    public void setSavingsDeficit(Amount deficit) {
        savingsDeficit.setValue(deficit);
    }

    /**
     * Calculates all information related to the user's savings.
     * These are:
     * 1. Remaining budget for this month
     * 2. Amount saved for this month currently
     * 3. Budget deficit (if remaining budget is negative)
     * 4. Savings deficit (if amount saved is negative)
     *
     * @param transactions The current list of transactions.
     */
    public void calculateBudgetInfo(TransactionList transactions) {
        totalExpenses = BigDecimal.ZERO;
        totalIncomes = BigDecimal.ZERO;
        for (Transaction transaction: transactions) {
            if (transaction instanceof Expense) {
                totalExpenses = totalExpenses.add(transaction.getAmount().getValue());
            } else {
                totalIncomes = totalIncomes.add(transaction.getAmount().getValue());
            }
        }
        calculateRemainingBudget();
        calculateCurrentSavings();
    }

    /**
     * Calculates the user's remaining budget for the month.
     * Steps:
     * 1. Set the remaining budget to the user's monthly expense limit.
     * 2. For each expense in the list of transactions, deduct its amount from the remaining budget.
     * 3. The minimum remaining budget is capped at 0.
     */
    public void calculateRemainingBudget() {
        BigDecimal expenseLimit = monthlyExpenseLimit.getAmount().getValue();
        BigDecimal remainingBudget = expenseLimit.subtract(totalExpenses);
        if (remainingBudget.signum() < 0) {
            setRemainingBudget(ZERO_AMOUNT);
            setBudgetDeficit(new Amount(remainingBudget.negate().toString()));
        } else {
            setRemainingBudget(new Amount(remainingBudget.toString()));
            setBudgetDeficit(ZERO_AMOUNT);
        }
    }

    /**
     * Calculates the user's current savings for the month.
     * Steps:
     * 1. Set the remaining budget to the user's monthly expense limit.
     * 2. For each expense in the list of transactions, deduct its amount from the remaining budget.
     * 3. The minimum remaining budget is capped at 0.
     */
    public void calculateCurrentSavings() {
        BigDecimal savings = totalIncomes.subtract(totalExpenses);
        if (savings.signum() < 0) {
            setCurrentSavings(ZERO_AMOUNT);
            setSavingsDeficit(new Amount(savings.negate().toString()));
        } else {
            setCurrentSavings(new Amount(savings.toString()));
            setSavingsDeficit(ZERO_AMOUNT);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlyBudget // instanceof handles nulls
                && monthlyExpenseLimit.equals(((MonthlyBudget) other).monthlyExpenseLimit));
    }
}
