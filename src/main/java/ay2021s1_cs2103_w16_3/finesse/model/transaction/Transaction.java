package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import static ay2021s1_cs2103_w16_3.finesse.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ay2021s1_cs2103_w16_3.finesse.model.category.Category;

/**
 * Represents a Transaction in the finance tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Transaction {

    public static final Comparator<Transaction> TRANSACTION_COMPARATOR =
            Comparator.comparing((Transaction t) -> t.date).reversed().thenComparing(t -> t.title);

    public static final String MESSAGE_TITLE_CONSTRAINTS = "Transactions should only contain one title.";
    public static final String MESSAGE_AMOUNT_CONSTRAINTS = "Transactions should only contain one amount.";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Transactions should only contain one date.";

    // Identity fields
    private final Title title;
    private final Amount amount;
    private final Date date;

    // Data fields
    private final Set<Category> categories = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Transaction(Title title, Amount amount, Date date, Set<Category> categories) {
        requireAllNonNull(title, amount, date, categories);
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.categories.addAll(categories);
    }

    public Title getTitle() {
        return title;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public LocalDate getDateValue() {
        return date.getDate();
    }

    /**
     * Returns an immutable category set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, amount, date, categories);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate())
                .append(" Categories: ");
        getCategories().forEach(builder::append);
        return builder.toString();
    }

}
