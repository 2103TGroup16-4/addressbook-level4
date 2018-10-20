package seedu.address.model.document;

import seedu.address.model.person.ServedPatient;

/**
 * Represents the referral letter for the served patients.
 */
public class ReferralLetter extends Document {
    private static final String FILE_TYPE = "Referral Letter";
    private final String referralContent;

    /**
     * Creates a ReferralLetter object for the specified servedPatient.
     * @param servedPatient the patient who has already consulted the doctor.
     */
    public ReferralLetter(ServedPatient servedPatient) {
        setFileType(FILE_TYPE);
        setName(servedPatient.getName());
        setIcNumber(servedPatient.getIcNumber());
        this.referralContent = servedPatient.getReferralContent();
    }
}
