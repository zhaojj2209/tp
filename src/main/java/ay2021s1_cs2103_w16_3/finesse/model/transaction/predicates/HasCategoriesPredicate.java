package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import java.util.List;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Category}s matches any of the categories given.
 */
public class HasCategoriesPredicate implements Predicate<Transaction> {
    private final List<String> categories;

    public HasCategoriesPredicate(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public boolean test(Transaction transaction) {
        return categories.stream().anyMatch(category -> transaction.getCategories().stream()
                .map(x -> x.getCategoryName()).anyMatch(category::equalsIgnoreCase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasCategoriesPredicate // instanceof handles nulls
                && categories.equals(((HasCategoriesPredicate) other).categories)); // state check
    }

}
