package seedu.address.model.document;

import java.util.ArrayList;

import java.util.Map;

import seedu.address.model.medicine.Medicine;
import seedu.address.model.medicine.QuantityToDispense;
import seedu.address.model.person.ServedPatient;
import seedu.address.model.services.Service;
import seedu.address.model.tag.Tag;

/**
 * Represents the receipt for the served patients. This class is responsible for extracting information that is
 * relevant to the receipt.
 */
public class Receipt extends Document {
    public static final String FILE_TYPE = "Receipt";

    private float totalPrice;
    private Map<Medicine, QuantityToDispense> allocatedMedicine;
    private ArrayList<Service> servicesRendered;
    private Tag tag;

    /**
     * Creates a receipt object for the specified servedPatient.
     * @param servedPatient the patient who has already consulted the doctor.
     */
    public Receipt(ServedPatient servedPatient) {
        totalPrice = 0;
        setFileType(FILE_TYPE);
        setName(servedPatient.getName());
        setIcNumber(servedPatient.getIcNumber());
        this.allocatedMedicine = servedPatient.getMedicineAllocated();
        servicesRendered = new ArrayList<>();
        servicesRendered.add(Service.CONSULTATION);

        tag = servedPatient.getPatient().getTags().iterator().next();

        if (tag.toString().equals("[pioneer]")) {
            pioneerDiscount();
        }

        else if (tag.toString().equals("[blue]")) {
            blueDiscount();
        }

    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void increaseTotalPriceBy(float increment) {
        totalPrice += increment;
    }

    public void resetPrice() {
        totalPrice = 0;
    }

    public void pioneerDiscount() {
        totalPrice -= 28.5;
    }

    public void blueDiscount() {
        totalPrice -= 18.5;
    }

    public Map<Medicine, QuantityToDispense> getAllocatedMedicine() {
        return allocatedMedicine;
    }

    public ArrayList<Service> getServicesRendered() {
        return servicesRendered;
    }
}
