package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import seedu.address.model.medicine.Medicine;

/**
 * Uncompleted class, need to add more methods and fields.
 * Represents a patient that is currently consulting the doctor in the address book.
 * It has all methods needed to add information to generate various document later on.
 */
public class CurrentPatient {

    private ServedPatient patient;

    /**
     * Assigns a patient to the Current Patient.
     * @param patient which is going to be served.
     */
    public void assignPatient(Patient patient) {
        this.patient = new ServedPatient(patient);
    }

    /**
     * Removes the ServedPatient.
     * @return the removed ServedPatient
     */
    public ServedPatient finishServing() {
        ServedPatient temp = patient;
        patient = null;
        return temp;
    }

    /**
     * Add note content to the ServedPatient.
     */
    public String addNoteContent(String content) {
        requireNonNull(patient);
        return patient.addNoteContent(content);
    }

    /**
     * Add referral letter content to the ServedPatient.
     */
    public String addReferralContent(String content) {
        requireNonNull(patient);
        return patient.addReferralContent(content);
    }

    /**
     * Add Mc content to the ServedPatient.
     */
    public String addMcContent(String content) {
        requireNonNull(patient);
        return patient.addMcContent(content);
    }

    /**
     * Add specified quantity of medicine to patient.
     * @param medicine to be added.
     * @param quantity of medicinet to be added.
     * @return string representation of medicine added.
     */
    public String addMedicine(Medicine medicine, int quantity) {
        return patient.addMedicine(medicine, quantity);
    }

    /**
     * Returns the note content for the {@code served patient}.
     */
    private String getNoteContent() {
        requireNonNull(patient);
        return patient.getNoteContent();
    }
    /**
     * Returns the MC content for the {@code served patient}.
     */
    private String getMcContent() {
        requireNonNull(patient);
        return patient.getMcContent();
    }

    /**
     * Returns the referral content for the {@code served patient}.
     */
    private String getReferralContent() {
        requireNonNull(patient);
        return patient.getReferralContent();
    }

    /**
     * Returns the allocated medicine for the patient.
     */
    private Map<Medicine, Integer> getMedicineAllocated() {
        return patient.getMedicineAllocated();
    }

    /**
     * Returns true if there is Current Patient.
     */
    public boolean hasCurrentPatient() {
        return patient != null;
    }

    /**
     * Returns a console-friendly representation of the patient's documents.
     */
    public String toDocumentInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNotes: ")
                .append(getNoteContent())
                .append("\nMC Content: ")
                .append(getMcContent())
                .append("\nReferral Content: ")
                .append(getReferralContent());
        sb.append("\nMedicine Allocated: ");
        getMedicineAllocated().forEach((medicine, quantity) -> sb.append(medicine.toString() + ": " + quantity + "\n"));
        return sb.toString();
    }

    /**
     * @return a console-friendly representation of the patient.
     */
    public String toNameAndIc() {
        return patient.toNameAndIc();
    }

}
