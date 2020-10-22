package ay2021s1_cs2103_w16_3.finesse.model.budget;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

public class MonthlyBudget {
    private MonthlyExpenseLimit monthlyExpenseLimit;

    public MonthlyBudget() {
        monthlyExpenseLimit = new MonthlyExpenseLimit(new Amount("0"));
    }

    public MonthlyExpenseLimit getMonthlyExpenseLimit() {
        return monthlyExpenseLimit;
    }

    public void setMonthlyExpenseLimit(Amount limit) {
        monthlyExpenseLimit.setValue(limit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlyBudget // instanceof handles nulls
                && monthlyExpenseLimit.equals(((MonthlyBudget) other).monthlyExpenseLimit));
    }
}
