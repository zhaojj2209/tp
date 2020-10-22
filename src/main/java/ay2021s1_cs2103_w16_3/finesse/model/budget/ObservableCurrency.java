package ay2021s1_cs2103_w16_3.finesse.model.budget;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * An abstract class representing currency values.
 * Guarantees: immutable; is valid as checked by the Amount class
 */
public abstract class ObservableCurrency {

    private BigDecimal value;
    private final ObjectProperty<BigDecimal> observableValue;

    /**
     * Constructs a {@code ObservableCurrency}.
     *
     * @param amount A valid amount.
     */
    public ObservableCurrency(String amount) {
        requireNonNull(amount);
        value = new BigDecimal(amount.replaceFirst("^\\$", ""));
        observableValue = new SimpleObjectProperty<>(value);
        assert value.compareTo(BigDecimal.ZERO) >= 0; // amount should be non-negative
    }

    public BigDecimal getValue() {
        return value;
    }

    public ObjectProperty<BigDecimal> getObservableValue() {
        return observableValue;
    }

    public void setValue(String amount) {
        value = new BigDecimal(amount.replaceFirst("^\\$", ""));
        observableValue.setValue(value);
    }

    /**
     * Returns a String representation of this ObservableCurrency
     * that can be used to construct a new ObservableCurrency object.
     * @return A String representation of this ObservableCurrency.
     */
    @Override
    public String toString() {
        return String.format("$%.2f", value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
