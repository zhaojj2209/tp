package ay2021s1_cs2103_w16_3.finesse.model.budget;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

public class MonthlyExpenseLimitTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MonthlyExpenseLimit(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new MonthlyExpenseLimit(new Amount(invalidAmount)));
        assertThrows(IllegalArgumentException.class, () -> new MonthlyExpenseLimit(new Amount("40.404")));
    }

    @Test
    public void equals_distinctMonthlyExpenseLimitsWithSameAttributes_returnsTrue() {
        MonthlyExpenseLimit firstLimit = new MonthlyExpenseLimit(new Amount("3.78"));
        MonthlyExpenseLimit secondLimit = new MonthlyExpenseLimit(new Amount("3.78"));
        assertNotSame(firstLimit, secondLimit);
        assertEquals(firstLimit, secondLimit);
    }

    @Test
    public void hashCode_distinctMonthlyExpenseLimitsWithSameAttributes_returnsTrue() {
        MonthlyExpenseLimit firstLimit = new MonthlyExpenseLimit(new Amount("3.78"));
        MonthlyExpenseLimit secondLimit = new MonthlyExpenseLimit(new Amount("3.78"));
        assertNotSame(firstLimit, secondLimit);
        assertEquals(firstLimit.hashCode(), secondLimit.hashCode());
    }
}
