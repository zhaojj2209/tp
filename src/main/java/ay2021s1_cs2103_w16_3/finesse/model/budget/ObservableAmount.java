package ay2021s1_cs2103_w16_3.finesse.model.budget;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * An abstract class representing currency values.
 * Guarantees: mutable; has a valid Amount that is being observed
 */
public class ObservableAmount {

    private final ObjectProperty<Amount> observableAmount;

    /**
     * Constructs a {@code ObservableAmount} with an amount of $0.
     */
    public ObservableAmount() {
        observableAmount = new SimpleObjectProperty<>(new Amount("0"));
    }

    /**
     * Constructs a {@code ObservableAmount} with the specified amount.
     *
     * @param amount A valid amount.
     */
    public ObservableAmount(Amount amount) {
        requireNonNull(amount);
        observableAmount = new SimpleObjectProperty<>(amount);
    }

    public ObjectProperty<Amount> getObservableAmount() {
        return observableAmount;
    }

    public void setValue(Amount amount) {
        observableAmount.setValue(amount);
    }

    public Amount getAmount() {
        return observableAmount.get();
    }

    public BigDecimal getAmountValue() {
        return getAmount().getValue();
    }

    @Override
    public int hashCode() {
        return observableAmount.get().hashCode();
    }

}
