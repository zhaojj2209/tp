package ay2021s1_cs2103_w16_3.finesse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Title;

/**
 * JSON-friendly version of {@link MonthlyBudget}.
 */
class JsonAdaptedMonthlyBudget {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Monthly Budget's %s field is missing!";

    private final String expenseLimit;

    /**
     * Constructs a {@code JsonAdaptedMonthlyBudget} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedMonthlyBudget(@JsonProperty("expenseLimit") String expenseLimit) {
        this.expenseLimit = expenseLimit;
    }

    /**
     * Converts a given {@code MonthlyBudget} into this class for Jackson use.
     */
    public JsonAdaptedMonthlyBudget(MonthlyBudget source) {
        expenseLimit = source.getMonthlyExpenseLimit().getObservableAmount().get().toString();
    }

    /**
     * Converts this Jackson-friendly adapted monthly budget object into the model's {@code MonthlyBudget} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted monthly budget.
     */
    public MonthlyBudget toModelType() throws IllegalValueException {
        final MonthlyBudget monthlyBudget = new MonthlyBudget();
        if (expenseLimit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }

        if (!Amount.isValidAmount(expenseLimit)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }

        monthlyBudget.setMonthlyExpenseLimit(new Amount(expenseLimit));
        return monthlyBudget;
    }

}
