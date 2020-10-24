package ay2021s1_cs2103_w16_3.finesse.model.budget;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;

public class MonthlySavingsGoalTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MonthlySavingsGoal(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new MonthlySavingsGoal(new Amount(invalidAmount)));
        assertThrows(IllegalArgumentException.class, () -> new MonthlySavingsGoal(new Amount("40.404")));
    }

    @Test
    public void equals_distinctMonthlySavingsGoalsWithSameAttributes_returnsTrue() {
        MonthlySavingsGoal firstGoal = new MonthlySavingsGoal(new Amount("3.78"));
        MonthlySavingsGoal secondGoal = new MonthlySavingsGoal(new Amount("3.78"));
        assertNotSame(firstGoal, secondGoal);
        assertEquals(firstGoal, secondGoal);
    }

    @Test
    public void hashCode_distinctMonthlySavingsGoalsWithSameAttributes_returnsTrue() {
        MonthlySavingsGoal firstGoal = new MonthlySavingsGoal(new Amount("3.78"));
        MonthlySavingsGoal secondGoal = new MonthlySavingsGoal(new Amount("3.78"));
        assertNotSame(firstGoal, secondGoal);
        assertEquals(firstGoal.hashCode(), secondGoal.hashCode());
    }
}
