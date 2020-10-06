package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amounts
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only
        assertFalse(Amount.isValidAmount("phone")); // non-numeric
        assertFalse(Amount.isValidAmount("9011p041")); // alphabets within digits
        assertFalse(Amount.isValidAmount("9312 1534")); // spaces within digits
        assertFalse(Amount.isValidAmount("40.")); // no numbers after decimal
        assertFalse(Amount.isValidAmount("40.4")); // 1 decimal place
        assertFalse(Amount.isValidAmount("40.404")); // 3 decimal places
        assertFalse(Amount.isValidAmount("€3")); // wrong currency prefix

        // valid amounts
        assertTrue(Amount.isValidAmount("$1")); // $ prefix
        assertTrue(Amount.isValidAmount("4.20")); // 2 decimal places
        assertTrue(Amount.isValidAmount("$3.50")); // $ prefix, 2 decimal places
        assertTrue(Amount.isValidAmount("91")); // numbers
        assertTrue(Amount.isValidAmount("911")); // numbers
        assertTrue(Amount.isValidAmount("93121534")); // long numbers
        assertTrue(Amount.isValidAmount("124293842033123")); // long numbers
    }
}
