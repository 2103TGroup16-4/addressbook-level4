package seedu.address.model.person.medicalrecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import seedu.address.model.medicine.SerialNumber;

/**
 * Represents a note object belonging to a patient.
 */
public class Note {

    // Items belonging to a note object
    private Message message;
    private Map<SerialNumber, Quantity> dispensedMedicines;

    public Note(Message message, Map<SerialNumber, Quantity> dispensedMedicines) {
        this.message = message;
        this.dispensedMedicines = dispensedMedicines;
    }

    public Note(String message) {
        this.message = new Message(message);
        this.dispensedMedicines = new HashMap<>();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Map<SerialNumber, Quantity> getDispensedMedicines() {
        return dispensedMedicines;
    }

    public void setDispensedMedicines(Map<SerialNumber, Quantity> dispensedMedicines) {
        this.dispensedMedicines = dispensedMedicines;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[Message: ")
                .append(getMessage())
                .append(", Dispensed Medicines: ")
                .append(convertDispensedMedicinesToPrettyString())
                .append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(message, dispensedMedicines);
    }

    /**
     * Convert the Map to a nice looking string for readability.
     * @return Pretty string of dispensedMedicines
     */
    private String convertDispensedMedicinesToPrettyString() {
        String result = "";
        for (Map.Entry<SerialNumber, Quantity> entry: dispensedMedicines.entrySet()) {
            result += "<";
            result += entry.getKey();
            result += ", ";
            result += entry.getValue();
            result += ">, ";
        }
        return result.length() >= 2 ? result.substring(0, result.length() - 2) : result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return otherNote != null
                && otherNote.getMessage().equals(message)
                && otherNote.getDispensedMedicines().equals(dispensedMedicines);
    }
}
