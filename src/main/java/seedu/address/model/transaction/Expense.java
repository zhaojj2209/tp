package seedu.address.model.transaction;

import java.util.Set;

import seedu.address.model.category.Category;

public class Expense extends Transaction {
    public Expense(Name name, Amount amount, Date date, Set<Category> categories) {
        super(name, amount, date, categories);
    }
}
