package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditTransactionDescriptor;
import seedu.address.model.category.Category;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;

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
        descriptor.setName(transaction.getName());
        descriptor.setAmount(transaction.getAmount());
        descriptor.setDate(transaction.getDate());
        descriptor.setCategories(transaction.getCategories());
    }

    /**
     * Sets the {@code Name} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
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
