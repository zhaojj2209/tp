package ay2021s1_cs2103_w16_3.finesse.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import ay2021s1_cs2103_w16_3.finesse.model.AddressBook;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyAddressBook;
import ay2021s1_cs2103_w16_3.finesse.model.category.Category;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Name;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Transaction[] getSampleTransactions() {
        return new Transaction[] {
            new Transaction(new Name("Alex Yeoh"), new Amount("87438807"), new Date("alexyeoh@example.com"),
                getCategoriesSet("friends")),
            new Transaction(new Name("Bernice Yu"), new Amount("99272758"), new Date("berniceyu@example.com"),
                getCategoriesSet("colleagues", "friends")),
            new Transaction(new Name("Charlotte Oliveiro"), new Amount("93210283"), new Date("charlotte@example.com"),
                getCategoriesSet("neighbours")),
            new Transaction(new Name("David Li"), new Amount("91031282"), new Date("lidavid@example.com"),
                getCategoriesSet("family")),
            new Transaction(new Name("Irfan Ibrahim"), new Amount("92492021"), new Date("irfan@example.com"),
                getCategoriesSet("classmates")),
            new Transaction(new Name("Roy Balakrishnan"), new Amount("92624417"), new Date("royb@example.com"),
                getCategoriesSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Transaction sampleTransaction : getSampleTransactions()) {
            sampleAb.addTransaction(sampleTransaction);
        }
        return sampleAb;
    }

    /**
     * Returns a category set containing the list of strings given.
     */
    public static Set<Category> getCategoriesSet(String... strings) {
        return Arrays.stream(strings)
                .map(Category::new)
                .collect(Collectors.toSet());
    }

}