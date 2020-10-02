package seedu.address.model.transaction;

import java.util.Set;

import seedu.address.model.category.Category;

public class Income extends Transaction {
    public Income(Name name, Amount amount, Date date, Set<Category> categories) {
        super(name, amount, date, categories);
    }
}
