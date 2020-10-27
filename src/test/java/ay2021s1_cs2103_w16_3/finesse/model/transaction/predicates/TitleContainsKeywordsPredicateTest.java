package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate = new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate = new TitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different transaction -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("Damith"));
        assertTrue(predicate.test(new TransactionBuilder().withTitle("Damith Seth").buildExpense()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Damith", "Seth"));
        assertTrue(predicate.test(new TransactionBuilder().withTitle("Damith Seth").buildExpense()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Seth", "Aaron"));
        assertTrue(predicate.test(new TransactionBuilder().withTitle("Damith Aaron").buildExpense()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("dAMiTh", "SEtH"));
        assertTrue(predicate.test(new TransactionBuilder().withTitle("Damith Seth").buildExpense()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TransactionBuilder().withTitle("Damith").buildExpense()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Aaron"));
        assertFalse(predicate.test(new TransactionBuilder().withTitle("Damith Seth").buildExpense()));

        // Keywords match amount and date, but does not match title
        predicate = new TitleContainsKeywordsPredicate(
                Arrays.asList("12345", "damith@comp.nus.edu.sg", "COM2", "31/12/2019"));
        assertFalse(predicate.test(new TransactionBuilder().withTitle("Damith").withAmount("12345")
                .withDate("31/12/2019").buildExpense()));
    }
}
