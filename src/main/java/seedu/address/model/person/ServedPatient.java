package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.medicine.Medicine;

/**
 * Uncompleted class, need to add more methods and fields.
 * Represents a patient that has already consulted the doctor in the address book.
 * It has all the necessary data needed to generate any object that inherits from Document.
 */
public class ServedPatient {

    private final Patient patient;
    private String noteContent;
    private String referralContent;
    private String mcContent;
    private Map<Medicine, Integer> medicineAllocated;
    // add more fields as required

    /**
     * Constructs a servedPatient object from a patient object to extract all the information
     * necessary for printing a document.
     *
     * @param patient A valid patient.
     */
    public ServedPatient(Patient patient) {
        requireNonNull(patient);
        medicineAllocated = new HashMap<>();
        this.patient = patient;
        this.noteContent = "";
        this.referralContent = "";
        this.mcContent = "";
    }

    /**
     * Returns the name of the {@code served patient}.
     */
    public Name getName() {
        return patient.getName();
    }

    /**
     * Returns the IC Number of the {@code served patient}.
     */
    public IcNumber getIcNumber() {
        return patient.getIcNumber();
    }

    /**
     * Returns the note content of the {@code served patient}.
     */
    public String getNoteContent() {
        return this.noteContent;
    }
    /**
     * Returns the MC content of the {@code served patient}.
     */
    public String getMcContent() {
        return this.mcContent;
    }

    /**
     * Returns the referral content of the {@code served patient}.
     */
    public String getReferralContent() {
        return this.referralContent;
    }

    /**
     * Returns the medicine and quantity allocated to the {@code served patient}.
     */
    public Map<Medicine, Integer> getMedicineAllocated() {
        return medicineAllocated;
    }

    /**
     * Skeleton to add note content.
     */
    public String addNoteContent(String content) {
        return noteContent = content;
    }

    /**
     * Skeleton to add referral content.
     */
    public String addReferralContent(String content) {
        return referralContent = content;
    }

    /**
     * Skeleton to add Mc Content.
     */
    public String addMcContent(String content) {
        return mcContent = content;
    }

    /**
     * Add specified quantity of medicine to patient.
     * @param medicine to be added.
     * @param quantity of medicinet to be added.
     * @return string representation of medicine added.
     */
    public String addMedicine(Medicine medicine, int quantity) {
        medicineAllocated.put(medicine, quantity);
        return medicine.toString();
    }

    /**
     * Checks whether current is a specified patient.
     * @return true if queue contains patient.
     */
    public boolean isPatient(Patient patient) {
        return patient.isSamePerson(patient);
    }

    /**
     * @return a console-friendly representation of the patient.
     */
    public String toNameAndIc() {
        return patient.toNameAndIc();
    }
}
