package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.ALICE;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class TransactionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Transaction transaction = new TransactionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> transaction.getCategories().remove(0));
    }

    @Test
    public void isSameTransaction() {
        // same object -> returns true
        assertTrue(ALICE.isSameTransaction(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTransaction(null));

        // different amount and date -> returns false
        Transaction editedAlice = new TransactionBuilder(ALICE).withAmount(VALID_AMOUNT_BOB)
                .withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.isSameTransaction(editedAlice));

        // different title -> returns false
        editedAlice = new TransactionBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.isSameTransaction(editedAlice));

        // same title, same amount, different attributes -> returns true
        editedAlice = new TransactionBuilder(ALICE).withDate(VALID_DATE_BOB).withCategories(VALID_CATEGORY_HUSBAND)
                .build();
        assertTrue(ALICE.isSameTransaction(editedAlice));

        // same title, same date, different attributes -> returns true
        editedAlice = new TransactionBuilder(ALICE).withAmount(VALID_AMOUNT_BOB).withCategories(VALID_CATEGORY_HUSBAND)
                .build();
        assertTrue(ALICE.isSameTransaction(editedAlice));

        // same title, same amount, same date, different attributes -> returns true
        editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameTransaction(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Transaction aliceCopy = new TransactionBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different transaction -> returns false
        assertFalse(ALICE.equals(BOB));

        // different title -> returns false
        Transaction editedAlice = new TransactionBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different amounts -> returns false
        editedAlice = new TransactionBuilder(ALICE).withAmount(VALID_AMOUNT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different dates -> returns false
        editedAlice = new TransactionBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different categories -> returns false
        editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
