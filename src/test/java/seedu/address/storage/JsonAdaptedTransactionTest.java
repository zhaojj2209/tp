package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTransaction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Name;

public class JsonAdaptedTransactionTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_AMOUNT = "+651234";
    private static final String INVALID_DATE = "example.com";
    private static final String INVALID_CATEGORY = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_AMOUNT = BENSON.getAmount().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = BENSON.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTransactionDetails_returnsTransaction() throws Exception {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(BENSON);
        assertEquals(BENSON, transaction.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(INVALID_NAME, VALID_AMOUNT, VALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(null, VALID_AMOUNT, VALID_DATE,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_NAME, INVALID_AMOUNT, VALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(VALID_NAME, null, VALID_DATE,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_NAME, VALID_AMOUNT, INVALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(VALID_NAME, VALID_AMOUNT, null,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORY));
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_NAME, VALID_AMOUNT, VALID_DATE, invalidCategories);
        assertThrows(IllegalValueException.class, transaction::toModelType);
    }

}
