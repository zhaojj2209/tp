package ay2021s1_cs2103_w16_3.finesse.model.category;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidCategoryName_throwsIllegalArgumentException() {
        String invalidCategoryName = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategoryName));
    }

    @Test
    public void isValidCategoryName() {
        // null category name
        assertThrows(NullPointerException.class, () -> Category.isValidCategoryName(null));
    }

}
