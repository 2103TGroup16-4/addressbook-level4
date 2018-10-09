package seedu.address.model.medicine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class MedicineNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new MedicineName(null));
    }

    @Test
    public void constructor_invalidMedicineName_throwsIllegalArgumentException() {
        String invalidMedicineName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new MedicineName(invalidMedicineName));
    }

    @Test
    public void isValidMedicineName() {
        // null medicine name
        Assert.assertThrows(NullPointerException.class, () -> MedicineName.isValidMedicineName(null));

        // invalid medicine names
        assertFalse(MedicineName.isValidMedicineName("")); // empty string
        assertFalse(MedicineName.isValidMedicineName(" ")); // spaces only
        assertFalse(MedicineName.isValidMedicineName("&")); // only non-alphanumeric characters
        assertFalse(MedicineName.isValidMedicineName("paracetamol#")); // contains non-alphanumeric character

        // valid medicine names
        assertTrue(MedicineName.isValidMedicineName("panadol flumax")); // alphabets only
        assertTrue(MedicineName.isValidMedicineName("12345")); // numbers only
        assertTrue(MedicineName.isValidMedicineName("penicillin 23")); // alphanumeric characters
        assertTrue(MedicineName.isValidMedicineName("Mescaline Bioline")); // with capital letters
        assertTrue(MedicineName.isValidMedicineName("Alcoholic Methylbenzene "
                + "with 2nd group substituted with OH group")); // long names
    }
}
