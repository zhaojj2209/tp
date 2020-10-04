package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import java.util.Set;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.model.category.Category;

public class Expense extends Transaction {

    /** {@code Predicate} that returns true only for {@code Expense} transactions */
    public static final Predicate<Transaction> PREDICATE_SHOW_ALL_EXPENSES =
        transaction -> transaction instanceof Expense;

    public Expense(Name name, Amount amount, Date date, Set<Category> categories) {
        super(name, amount, date, categories);
    }
}
