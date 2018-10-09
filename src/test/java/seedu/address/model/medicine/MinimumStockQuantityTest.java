package seedu.address.model.medicine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class MinimumStockQuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new MinimumStockQuantity(null));
    }

    @Test
    public void constructor_invalidMinimumStockQuantity_throwsIllegalArgumentException() {
        String invalidMinimumStockQuantity = "";
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new MinimumStockQuantity(invalidMinimumStockQuantity));
    }

    @Test
    public void isValidMinimumStockQuantity() {
        // null minimum stock quantity
        Assert.assertThrows(NullPointerException.class, () -> MinimumStockQuantity.isValidMinimumStockQuantity(null));

        // invalid minimum stock quantities
        assertFalse(MinimumStockQuantity.isValidMinimumStockQuantity(""));
        assertFalse(MinimumStockQuantity.isValidMinimumStockQuantity(" ")); // spaces only
        assertFalse(MinimumStockQuantity.isValidMinimumStockQuantity("phone")); // non-numeric
        assertFalse(MinimumStockQuantity.isValidMinimumStockQuantity("9011p041")); // alphabets within digits
        assertFalse(MinimumStockQuantity.isValidMinimumStockQuantity("9312 1534")); // spaces within digits
        assertFalse(MinimumStockQuantity.isValidMinimumStockQuantity("0")); // cannot be 0

        // valid minimum stock quantities
        assertTrue(MinimumStockQuantity.isValidMinimumStockQuantity("1"));
        assertTrue(MinimumStockQuantity.isValidMinimumStockQuantity("999"));
        assertTrue(MinimumStockQuantity.isValidMinimumStockQuantity("11"));
    }
}
