package ay2021s1_cs2103_w16_3.finesse.model.transaction.predicates;

import java.util.List;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.commons.util.StringUtil;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeyphrasesPredicate implements Predicate<Transaction> {
    private final List<String> keyphrases;

    public TitleContainsKeyphrasesPredicate(List<String> keyphrases) {
        this.keyphrases = keyphrases;
    }

    @Override
    public boolean test(Transaction transaction) {
        return keyphrases.stream()
                .anyMatch(keyphrase -> StringUtil.containsIgnoreCase(transaction.getTitle().toString(), keyphrase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeyphrasesPredicate // instanceof handles nulls
                && keyphrases.equals(((TitleContainsKeyphrasesPredicate) other).keyphrases)); // state check
    }

}
