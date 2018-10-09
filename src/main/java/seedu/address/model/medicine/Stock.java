package seedu.address.model.medicine;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of medicine in clinic.
 * Guarantees: immutable; is valid as declared in {@link #isValidStock(String)}
 */
public class Stock {
    public static final String MESSAGE_STOCK_CONSTRAINTS =
            "Stock should only contain numbers";
    public static final String STOCK_VALIDATION_REGEX = "^[1-9]+[0-9]*$";
    public final String value;

    /**
     * Constructs a {@code Stock}.
     *
     * @param number A valid number for stock.
     */
    public Stock(String number) {
        requireNonNull(number);
        checkArgument(isValidStock(number), MESSAGE_STOCK_CONSTRAINTS);
        value = number;
    }

    /**
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidStock(String test) {
        return test.matches(STOCK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Stock // instanceof handles nulls
                && value.equals(((Stock) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
