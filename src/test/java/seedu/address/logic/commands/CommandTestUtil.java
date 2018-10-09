package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ICNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINIMUM_STOCK_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_PER_UNIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.medicine.MedicineNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Patient;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.MedicineDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_ICNUMBER_AMY = "S1234567X";
    public static final String VALID_ICNUMBER_BOB = "S1234567X";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String ICNUMBER_DESC_AMY = " " + PREFIX_ICNUMBER + VALID_ICNUMBER_AMY;
    public static final String ICNUMBER_DESC_BOB = " " + PREFIX_ICNUMBER + VALID_ICNUMBER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final String VALID_MEDICINE_NAME_PANADOL = "Panadol";
    public static final String VALID_MEDICINE_NAME_ZYRTEC = "Zyrtec";
    public static final String VALID_MINIMUM_STOCK_QUANTITY_PANADOL = "100";
    public static final String VALID_MINIMUM_STOCK_QUANTITY_ZYRTEC = "250";
    public static final String VALID_PRICE_PER_UNIT_PANADOL = "1";
    public static final String VALID_PRICE_PER_UNIT_ZYRTEC = "2";
    public static final String VALID_SERIAL_NUMBER_PANADOL = "00293756";
    public static final String VALID_SERIAL_NUMBER_ZYRTEC = "12348293";
    public static final String VALID_STOCK_PANADOL = "1000";
    public static final String VALID_STOCK_ZYRTEC = "2000";

    public static final String INVALID_MEDICINE_NAME_DESC =
            " " + PREFIX_MEDICINE_NAME + "Dermasone#"; // '#' not allowed in medicine names
    public static final String INVALID_MINIMUM_STOCK_QUANTITY_DESC =
            " " + PREFIX_MINIMUM_STOCK_QUANTITY + "12s"; // 's' not allowed in minimum stock quantity
    public static final String INVALID_PRICE_PER_UNIT_DESC =
            " " + PREFIX_PRICE_PER_UNIT + "34#"; // '#' not allowed in price per unit
    public static final String INVALID_SERIAL_NUMBER_DESC =
            " " + PREFIX_SERIAL_NUMBER + "2308!239"; // '!' not allowed in serial number
    public static final String INVALID_STOCK_DESC =
            " " + PREFIX_STOCK + "23e2"; // 'e' not allowed in stock number

    public static final EditMedicineCommand.MedicineDescriptor DESC_PANADOL;
    public static final EditMedicineCommand.MedicineDescriptor DESC_ZYRTEC;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_PANADOL = new MedicineDescriptorBuilder().withMedicineName(VALID_MEDICINE_NAME_PANADOL)
                .withMinimumStockQuantity(VALID_MINIMUM_STOCK_QUANTITY_PANADOL)
                .withPricePerUnit(VALID_PRICE_PER_UNIT_PANADOL)
                .withSerialNumber(VALID_SERIAL_NUMBER_PANADOL)
                .withStock(VALID_STOCK_PANADOL).build();
        DESC_ZYRTEC = new MedicineDescriptorBuilder().withMedicineName(VALID_MEDICINE_NAME_ZYRTEC)
                .withMinimumStockQuantity(VALID_MINIMUM_STOCK_QUANTITY_ZYRTEC)
                .withPricePerUnit(VALID_PRICE_PER_UNIT_ZYRTEC)
                .withSerialNumber(VALID_SERIAL_NUMBER_ZYRTEC)
                .withStock(VALID_STOCK_ZYRTEC).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered patient list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Patient> expectedFilteredPatientList = new ArrayList<>(actualModel.getFilteredPersonList());
        List<Medicine> expectedFilteredMedicineList = new ArrayList<>(actualModel.getFilteredMedicineList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredPatientList, actualModel.getFilteredPersonList());
            assertEquals(expectedFilteredMedicineList, actualModel.getFilteredMedicineList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Patient patient = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Deletes the first patient in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        Patient firstPatient = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPatient);
        model.commitAddressBook();
    }

    /**
     * Updates {@code model}'s filtered list to show only the medicine at the given {@code targetIndex} in the
     * {@code model}'s records.
     */
    public static void showMedicineAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMedicineList().size());

        Medicine medicine = model.getFilteredMedicineList().get(targetIndex.getZeroBased());
        final String[] splitName = medicine.getMedicineName().fullName.split("\\s+");
        model.updateFilteredMedicineList(new MedicineNameContainsKeywordsPredicate((Arrays.asList(splitName[0]))));

        assertEquals(1, model.getFilteredMedicineList().size());
    }

    /**
     * Deletes the first medicine in {@code model}'s filtered list from {@code model}'s records.
     */
    public static void deleteFirstMedicine(Model model) {
        Medicine firstMedicine = model.getFilteredMedicineList().get(0);
        model.deleteMedicine(firstMedicine);
        model.commitAddressBook();
    }
}
