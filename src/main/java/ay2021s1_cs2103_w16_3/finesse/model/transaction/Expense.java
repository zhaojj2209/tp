package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import java.util.Set;

import ay2021s1_cs2103_w16_3.finesse.model.category.Category;

public class Expense extends Transaction {

    public Expense(Title title, Amount amount, Date date, Set<Category> categories) {
        super(title, amount, date, categories);
    }

}
