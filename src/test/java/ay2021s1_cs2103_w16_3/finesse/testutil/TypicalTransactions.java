package ay2021s1_cs2103_w16_3.finesse.testutil;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_FRIEND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ay2021s1_cs2103_w16_3.finesse.model.AddressBook;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {

    public static final Transaction ALICE = new TransactionBuilder().withTitle("Alice Pauline")
            .withDate("alice@example.com").withAmount("94351253").withCategories("friends").build();
    public static final Transaction BENSON = new TransactionBuilder().withTitle("Benson Meier")
            .withDate("johnd@example.com").withAmount("98765432").withCategories("owesMoney", "friends").build();
    public static final Transaction CARL = new TransactionBuilder().withTitle("Carl Kurz").withAmount("95352563")
            .withDate("heinz@example.com").build();
    public static final Transaction DANIEL = new TransactionBuilder().withTitle("Daniel Meier").withAmount("87652533")
            .withDate("cornelia@example.com").withCategories("friends").build();
    public static final Transaction ELLE = new TransactionBuilder().withTitle("Elle Meyer").withAmount("9482224")
            .withDate("werner@example.com").build();
    public static final Transaction FIONA = new TransactionBuilder().withTitle("Fiona Kunz").withAmount("9482427")
            .withDate("lydia@example.com").build();
    public static final Transaction GEORGE = new TransactionBuilder().withTitle("George Best").withAmount("9482442")
            .withDate("anna@example.com").build();

    // Manually added
    public static final Transaction HOON = new TransactionBuilder().withTitle("Hoon Meier").withAmount("8482424")
            .withDate("stefan@example.com").build();
    public static final Transaction IDA = new TransactionBuilder().withTitle("Ida Mueller").withAmount("8482131")
            .withDate("hans@example.com").build();

    // Manually added - Transaction's details found in {@code CommandTestUtil}
    public static final Transaction AMY = new TransactionBuilder().withTitle(VALID_TITLE_AMY)
            .withAmount(VALID_AMOUNT_AMY).withDate(VALID_DATE_AMY).withCategories(VALID_CATEGORY_FRIEND).build();
    public static final Transaction BOB = new TransactionBuilder().withTitle(VALID_TITLE_BOB)
            .withAmount(VALID_AMOUNT_BOB).withDate(VALID_DATE_BOB)
            .withCategories(VALID_CATEGORY_HUSBAND, VALID_CATEGORY_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTransactions() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical transactions.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Transaction transaction : getTypicalTransactions()) {
            ab.addTransaction(transaction);
        }
        return ab;
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
