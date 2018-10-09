package seedu.address.model.medicine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class PricePerUnitTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new PricePerUnit(null));
    }

    @Test
    public void constructor_invalidPricePerUnit_throwsIllegalArgumentException() {
        String invalidPricePerUnit = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new PricePerUnit(invalidPricePerUnit));
    }

    @Test
    public void isValidPricePerUnit() {
        // null price per unit
        Assert.assertThrows(NullPointerException.class, () -> PricePerUnit.isValidPricePerUnit(null));

        // invalid price per unit
        assertFalse(PricePerUnit.isValidPricePerUnit("")); // empty string
        assertFalse(PricePerUnit.isValidPricePerUnit(" ")); // spaces only
        assertFalse(PricePerUnit.isValidPricePerUnit("ppu")); // non-numeric
        assertFalse(PricePerUnit.isValidPricePerUnit("9011p041")); // alphabets within digits
        assertFalse(PricePerUnit.isValidPricePerUnit("9312 1534")); // spaces within digits

        // valid price per unit
        assertTrue(PricePerUnit.isValidPricePerUnit("911"));
        assertTrue(PricePerUnit.isValidPricePerUnit("124293842033123")); // long price per unit
    }
}
