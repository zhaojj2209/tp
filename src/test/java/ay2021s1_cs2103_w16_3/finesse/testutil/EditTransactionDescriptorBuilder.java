package ay2021s1_cs2103_w16_3.finesse.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.EditCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.EditCommand.EditTransactionDescriptor;
import ay2021s1_cs2103_w16_3.finesse.model.category.Category;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Title;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * A utility class to help with building EditTransactionDescriptor objects.
 */
public class EditTransactionDescriptorBuilder {

    private EditCommand.EditTransactionDescriptor descriptor;

    public EditTransactionDescriptorBuilder() {
        descriptor = new EditCommand.EditTransactionDescriptor();
    }

    public EditTransactionDescriptorBuilder(EditCommand.EditTransactionDescriptor descriptor) {
        this.descriptor = new EditTransactionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTransactionDescriptor} with fields containing {@code transaction}'s details
     */
    public EditTransactionDescriptorBuilder(Transaction transaction) {
        descriptor = new EditTransactionDescriptor();
        descriptor.setTitle(transaction.getTitle());
        descriptor.setAmount(transaction.getAmount());
        descriptor.setDate(transaction.getDate());
        descriptor.setCategories(transaction.getCategories());
    }

    /**
     * Sets the {@code Title} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and set it to the {@code EditTransactionDescriptor}
     * that we are building.
     */
    public EditTransactionDescriptorBuilder withCategories(String... categories) {
        Set<Category> categorySet = Stream.of(categories).map(Category::new).collect(Collectors.toSet());
        descriptor.setCategories(categorySet);
        return this;
    }

    public EditCommand.EditTransactionDescriptor build() {
        return descriptor;
    }
}
