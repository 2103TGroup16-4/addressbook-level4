package seedu.address.model.medicine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class StockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Stock(null));
    }

    @Test
    public void constructor_invalidStock_throwsIllegalArgumentException() {
        String invalidStock = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Stock(invalidStock));
    }

    @Test
    public void isValidStock() {
        // null stock number
        Assert.assertThrows(NullPointerException.class, () -> Stock.isValidStock(null));

        // invalid stock numbers
        assertFalse(Stock.isValidStock("")); // empty string
        assertFalse(Stock.isValidStock(" ")); // spaces only
        assertFalse(Stock.isValidStock("stock")); // non-numeric
        assertFalse(Stock.isValidStock("1p01")); // alphabets within digits
        assertFalse(Stock.isValidStock("92 34")); // spaces within digits
        assertFalse(Stock.isValidStock("0")); // cannot be zero

        // valid stock numbers
        assertTrue(Stock.isValidStock("1")); // first case
        assertTrue(Stock.isValidStock("145")); // normal level
        assertTrue(Stock.isValidStock("124293842033123")); // long stock number
    }
}
