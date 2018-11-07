package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.MedicineCardHandle;
import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.Patient;
import seedu.address.ui.PersonCard;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    private static final String LABEL_DEFAULT_STYLE = "label";

    private static final String BLUE_CHAS_COLOUR = "blue";
    private static final String ORANGE_CHAS_COLOUR = "orange";
    private static final String PIONEER_CHAS_COLOUR = "red";

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(PersonCardHandle expectedCard, PersonCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAddress(), actualCard.getAddress());
        assertEquals(expectedCard.getEmail(), actualCard.getEmail());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getPhone(), actualCard.getPhone());
        assertEquals(expectedCard.getTags(), actualCard.getTags());

        expectedCard.getTags().forEach(tag ->
                assertEquals(expectedCard.getTagStyleClasses(tag), actualCard.getTagStyleClasses(tag)));
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPatient}.
     */
    public static void assertCardDisplaysPerson(Patient expectedPatient, PersonCardHandle actualCard) {
        assertEquals(expectedPatient.getName().fullName, actualCard.getName());
        assertEquals(expectedPatient.getPhone().value, actualCard.getPhone());
        assertEquals(expectedPatient.getEmail().value, actualCard.getEmail());
        assertEquals(expectedPatient.getAddress().value, actualCard.getAddress());

        assertTagsEqual(expectedPatient, actualCard);
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedMedicine}.
     */
    public static void assertCardDisplaysMedicine(Medicine expectedMedicine, MedicineCardHandle actualCard) {
        assertEquals(expectedMedicine.getMedicineName().fullName, actualCard.getMedicineName());
        assertEquals(expectedMedicine.getSerialNumber().value, actualCard.getSerialNumber());
        assertEquals(expectedMedicine.getPricePerUnit().value, actualCard.getPricePerUnit());
        assertEquals(expectedMedicine.getMinimumStockQuantity().value.toString(), actualCard.getMinStockQuantity());
        assertEquals(expectedMedicine.getStock().value.toString(), actualCard.getStock());
    }

    /**
     * Returns the color style for {@code tagName}'s label. The tag's color is determined by looking up the color
     * in {@code PersonCard#TAG_COLOR_STYLES}, using an index generated by the hash code of the tag's content.
     *
     * @see PersonCard#getTagColorStyleFor(String)
     */

    private static String getTagColorStyleFor(String tagName) {
        switch (tagName) {
        case "blue":
            return BLUE_CHAS_COLOUR;
        case "orange":
            return ORANGE_CHAS_COLOUR;
        case "pioneer":
            return PIONEER_CHAS_COLOUR;
        default:
            throw new AssertionError(tagName + " does not have a color assigned.");
        }
    }
    /**
     * Asserts that the tags in {@code actualCard} matches all the tags in {@code expectedPerson} with the correct
     * color.
     */
    private static void assertTagsEqual(Patient expectedPatient, PersonCardHandle actualCard) {
        List<String> expectedTags = expectedPatient.getTags().stream()
                .map(tag -> tag.tagName).collect(Collectors.toList());
        assertEquals(expectedTags, actualCard.getTags());
        expectedTags.forEach(tag ->
                assertEquals(Arrays.asList(LABEL_DEFAULT_STYLE, getTagColorStyleFor(tag)),
                        actualCard.getTagStyleClasses(tag)));
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code patients} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, Patient... patients) {
        for (int i = 0; i < patients.length; i++) {
            personListPanelHandle.navigateToCard(i);
            assertCardDisplaysPerson(patients[i], personListPanelHandle.getPersonCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code patients} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, List<Patient> patients) {
        assertListMatching(personListPanelHandle, patients.toArray(new Patient[0]));
    }

    /**
     * Asserts the size of the list in {@code personListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(PersonListPanelHandle personListPanelHandle, int size) {
        int numberOfPeople = personListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
