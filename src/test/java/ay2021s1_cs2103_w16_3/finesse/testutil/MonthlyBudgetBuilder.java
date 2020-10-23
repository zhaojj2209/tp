package ay2021s1_cs2103_w16_3.finesse.testutil;

import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

/**
 * A utility class to help with building MonthlyBudget objects.
 */
public class MonthlyBudgetBuilder {

    public static final String DEFAULT_AMOUNT = "$500.00";

    private Amount monthlyExpenseLimit;

    /**
     * Creates a {@code MonthlyBudgetBuilder} with the default details.
     */
    public MonthlyBudgetBuilder() {
        monthlyExpenseLimit = new Amount(DEFAULT_AMOUNT);
    }

    /**
     * Initializes the MonthlyExpenseBuilder with the data of {@code transactionToCopy}.
     */
    public MonthlyBudgetBuilder(Amount limit) {
        monthlyExpenseLimit = limit;
    }

    /**
     * Builds a {@code MonthlyExpense} object.
     */
    public MonthlyBudget buildMonthlyBudget() {
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setMonthlyExpenseLimit(monthlyExpenseLimit);
        return monthlyBudget;
    }


}
