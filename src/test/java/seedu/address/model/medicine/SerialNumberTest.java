package seedu.address.model.medicine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class SerialNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Stock(null));
    }

    @Test
    public void constructor_invalidSerialNumber_throwsIllegalArgumentException() {
        String invalidSerialNumber = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new SerialNumber(invalidSerialNumber));
    }

    @Test
    public void isValidSerialNumber() {
        // null serial number
        Assert.assertThrows(NullPointerException.class, () -> SerialNumber.isValidSerialNumber(null));

        // invalid serial numbers
        assertFalse(SerialNumber.isValidSerialNumber("")); // empty string
        assertFalse(SerialNumber.isValidSerialNumber(" ")); // spaces only
        assertFalse(SerialNumber.isValidSerialNumber("91")); // less than 3 digits
        assertFalse(SerialNumber.isValidSerialNumber("serial")); // non-numeric
        assertFalse(SerialNumber.isValidSerialNumber("9011p041")); // alphabets within digits
        assertFalse(SerialNumber.isValidSerialNumber("9312 1534")); // spaces within digits

        // valid serial numbers
        assertTrue(SerialNumber.isValidSerialNumber("91156")); // exactly 3 digits
        assertTrue(SerialNumber.isValidSerialNumber("124293842033123")); // long digits
    }
}
