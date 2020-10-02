package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.category.Category;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_AMOUNT = "85355255";
    public static final String DEFAULT_DATE = "alice@gmail.com";

    private Name name;
    private Amount amount;
    private Date date;
    private Set<Category> categories;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        name = new Name(DEFAULT_NAME);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        categories = new HashSet<>();
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        name = transactionToCopy.getName();
        amount = transactionToCopy.getAmount();
        date = transactionToCopy.getDate();
        categories = new HashSet<>(transactionToCopy.getCategories());
    }

    /**
     * Sets the {@code Name} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and set it to the {@code Transaction} that we are
     * building.
     */
    public TransactionBuilder withCategories(String ... categories) {
        this.categories = SampleDataUtil.getCategoriesSet(categories);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public Transaction build() {
        return new Transaction(name, amount, date, categories);
    }

}
