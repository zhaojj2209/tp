package ay2021s1_cs2103_w16_3.finesse.model.budget;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MonthlyExpenseLimitTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MonthlyExpenseLimit(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new MonthlyExpenseLimit(invalidAmount));
        assertThrows(IllegalArgumentException.class, () -> new MonthlyExpenseLimit("40.404"));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> MonthlyExpenseLimit.isValidAmount(null));

        // invalid amounts
        assertFalse(MonthlyExpenseLimit.isValidAmount("")); // empty string
        assertFalse(MonthlyExpenseLimit.isValidAmount(" ")); // spaces only
        assertFalse(MonthlyExpenseLimit.isValidAmount("phone")); // non-numeric
        assertFalse(MonthlyExpenseLimit.isValidAmount("9011p041")); // alphabets within digits
        assertFalse(MonthlyExpenseLimit.isValidAmount("9312 1534")); // spaces within digits
        assertFalse(MonthlyExpenseLimit.isValidAmount("40.")); // no numbers after decimal
        assertFalse(MonthlyExpenseLimit.isValidAmount("40.4")); // 1 decimal place
        assertFalse(MonthlyExpenseLimit.isValidAmount("40.404")); // 3 decimal places
        assertFalse(MonthlyExpenseLimit.isValidAmount("€3")); // wrong currency prefix

        // valid amounts
        assertTrue(MonthlyExpenseLimit.isValidAmount("$1")); // $ prefix
        assertTrue(MonthlyExpenseLimit.isValidAmount("4.20")); // 2 decimal places
        assertTrue(MonthlyExpenseLimit.isValidAmount("$3.50")); // $ prefix, 2 decimal places
        assertTrue(MonthlyExpenseLimit.isValidAmount("91")); // numbers
        assertTrue(MonthlyExpenseLimit.isValidAmount("911")); // numbers
        assertTrue(MonthlyExpenseLimit.isValidAmount("93121534")); // long numbers
        assertTrue(MonthlyExpenseLimit.isValidAmount("124293842033123")); // long numbers
    }

    @Test
    public void equals_distinctMonthlyExpenseLimitsWithSameAttributes_returnsTrue() {
        MonthlyExpenseLimit firstLimit = new MonthlyExpenseLimit("3.78");
        MonthlyExpenseLimit secondLimit = new MonthlyExpenseLimit("3.78");
        assertNotSame(firstLimit, secondLimit);
        assertEquals(firstLimit, secondLimit);
    }

    @Test
    public void hashCode_distinctMonthlyExpenseLimitsWithSameAttributes_returnsTrue() {
        MonthlyExpenseLimit firstLimit = new MonthlyExpenseLimit("3.78");
        MonthlyExpenseLimit secondLimit = new MonthlyExpenseLimit("3.78");
        assertNotSame(firstLimit, secondLimit);
        assertEquals(firstLimit.hashCode(), secondLimit.hashCode());
    }
}
