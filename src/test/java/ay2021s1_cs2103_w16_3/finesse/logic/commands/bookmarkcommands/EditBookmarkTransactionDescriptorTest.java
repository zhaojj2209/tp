package ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmarkcommands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_PHONE_BILL;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_SPOTIFY_SUBSCRIPTION;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_SPOTIFY_SUBSCRIPTION;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_WORK;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_SPOTIFY_SUBSCRIPTION;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark.EditBookmarkTransactionDescriptor;
import ay2021s1_cs2103_w16_3.finesse.testutil.EditBookmarkTransactionDescriptorBuilder;

public class EditBookmarkTransactionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditBookmarkTransactionDescriptor descriptorWithSameValues =
                new EditBookmarkTransactionDescriptor(DESC_PHONE_BILL);
        assertTrue(DESC_PHONE_BILL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PHONE_BILL.equals(DESC_PHONE_BILL));

        // null -> returns false
        assertFalse(DESC_PHONE_BILL.equals(null));

        // different types -> returns false
        assertFalse(DESC_PHONE_BILL.equals(5));

        // different values -> returns false
        assertFalse(DESC_PHONE_BILL.equals(DESC_SPOTIFY_SUBSCRIPTION));

        // different title -> returns false
        EditBookmarkTransactionDescriptor editBookmarkTransactionDescriptor =
                new EditBookmarkTransactionDescriptorBuilder(DESC_PHONE_BILL)
                .withTitle(VALID_TITLE_SPOTIFY_SUBSCRIPTION).build();
        assertFalse(DESC_PHONE_BILL.equals(editBookmarkTransactionDescriptor));

        // different amount -> returns false
        editBookmarkTransactionDescriptor = new EditBookmarkTransactionDescriptorBuilder(DESC_PHONE_BILL)
                .withAmount(VALID_AMOUNT_SPOTIFY_SUBSCRIPTION).build();
        assertFalse(DESC_PHONE_BILL.equals(editBookmarkTransactionDescriptor));

        // different categories -> returns false
        editBookmarkTransactionDescriptor = new EditBookmarkTransactionDescriptorBuilder(DESC_PHONE_BILL)
                .withCategories(VALID_CATEGORY_WORK).build();
        assertFalse(DESC_PHONE_BILL.equals(editBookmarkTransactionDescriptor));
    }
}
