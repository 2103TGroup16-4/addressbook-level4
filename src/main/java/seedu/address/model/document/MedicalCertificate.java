package seedu.address.model.document;

import seedu.address.model.person.ServedPatient;

/**
 * Represents the medical certificate for the served patients.
 */
public class MedicalCertificate extends Document {
    private static final String FILE_TYPE = "Medical Certificate";
    private final String mcContent;

    /**
     * Creates a MedicalCertificate object for the specified servedPatient.
     * @param servedPatient the patient who has already consulted the doctor.
     */
    public MedicalCertificate(ServedPatient servedPatient) {
        setFileType(FILE_TYPE);
        setName(servedPatient.getName());
        setIcNumber(servedPatient.getIcNumber());
        this.mcContent = servedPatient.getMcContent();
    }
}
