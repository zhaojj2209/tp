package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import java.util.Set;

import ay2021s1_cs2103_w16_3.finesse.model.category.Category;

public class Income extends Transaction {

    public Income(Title title, Amount amount, Date date, Set<Category> categories) {
        super(title, amount, date, categories);
    }

}
