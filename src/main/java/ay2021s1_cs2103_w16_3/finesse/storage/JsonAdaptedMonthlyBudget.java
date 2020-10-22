package ay2021s1_cs2103_w16_3.finesse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;

/**
 * JSON-friendly version of {@link Expense}.
 */
class JsonAdaptedMonthlyBudget {

    private final String expenseLimit;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedMonthlyBudget(@JsonProperty("expenseLimit") String expenseLimit) {
        this.expenseLimit = expenseLimit;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedMonthlyBudget(MonthlyBudget source) {
        expenseLimit = source.getMonthlyExpenseLimit().getObservableValue().get().toString();
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public MonthlyBudget toModelType() {
        final MonthlyBudget monthlyBudget = new MonthlyBudget();
        if (expenseLimit != null) {
            monthlyBudget.setMonthlyExpenseLimit(new Amount(expenseLimit));
        }

        return monthlyBudget;
    }

}
