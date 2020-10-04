package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.DESC_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.EditCommand.EditTransactionDescriptor;
import ay2021s1_cs2103_w16_3.finesse.testutil.EditTransactionDescriptorBuilder;

public class EditTransactionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTransactionDescriptor descriptorWithSameValues = new EditCommand.EditTransactionDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different title -> returns false
        EditCommand.EditTransactionDescriptor editedAmy = new EditTransactionDescriptorBuilder(DESC_AMY)
                .withTitle(VALID_TITLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different amount -> returns false
        editedAmy = new EditTransactionDescriptorBuilder(DESC_AMY).withAmount(VALID_AMOUNT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different date -> returns false
        editedAmy = new EditTransactionDescriptorBuilder(DESC_AMY).withDate(VALID_DATE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different categories -> returns false
        editedAmy = new EditTransactionDescriptorBuilder(DESC_AMY).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
