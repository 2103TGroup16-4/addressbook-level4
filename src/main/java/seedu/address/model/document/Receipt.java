package seedu.address.model.document;

import java.util.Map;

import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.ServedPatient;

/**
 * Represents the receipt for the served patients.
 */
public class Receipt extends Document {
    private static final String HTML_TABLE_FORMATTING = "</td><td>";
    private static final String FILE_TYPE = "Receipt";
    private static final String RECEIPT_HEADER = "<table ID = \"contentTable\" width = 100%><col width = \"700\">";
    private static final String RECEIPT_HEADER_CONTENT = "<tr ID = \"receiptHeader\"><div class=\"contentHeader\">"
            + "<th>Prescription</th><th>Quantity</th><th>Unit Price</th><th>Total Price</th></div></tr>";
    private static final String RECEIPT_END_CONTENT_WITHOUT_PRICE = "<tr ID = \"receiptEnd\">"
            + "<td>Grand Total:" + HTML_TABLE_FORMATTING + "-" + HTML_TABLE_FORMATTING + "-" + HTML_TABLE_FORMATTING;
    private static final String RECEIPT_END =  "</td></tr></table>";


    private float totalPrice = 0;
    private Map<Medicine, Integer> allocatedMedicine;

    /**
     * Creates a receipt object for the specified servedPatient.
     * @param servedPatient the patient who has already consulted the doctor.
     */
    public Receipt(ServedPatient servedPatient) {
        setFileType(FILE_TYPE);
        setName(servedPatient.getName());
        setIcNumber(servedPatient.getIcNumber());
        allocatedMedicine = servedPatient.getMedicineAllocated();
    }

    /**
     * Formats all the relevant information of a receipt in HTML for the served patient.
     */
    String formatReceiptInformation() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(RECEIPT_HEADER)
                .append(RECEIPT_HEADER_CONTENT)
                .append(unpackTypesOfServices())
                .append(unpackMedicineAllocation(allocatedMedicine))
                .append(RECEIPT_END_CONTENT_WITHOUT_PRICE)
                .append(String.format("%.02f", totalPrice))
                .append(RECEIPT_END);
        return stringbuilder.toString();
    }

    /**
     * Extracts all the types of services rendered by the clinic for the served patient.
     */
    private String unpackTypesOfServices() {
        //placeholder
        //private String unpackConsultationInformation(Map<String, Integer> treatmentsReceived) {
        return "<tr><td>Consultation" + HTML_TABLE_FORMATTING + "1" + HTML_TABLE_FORMATTING + "30.00"
                + HTML_TABLE_FORMATTING + "30.00</td></tr>";
    }

    /**
     * Extracts all the medicines dispensed by the clinic for the served patient.
     */
    private String unpackMedicineAllocation(Map<Medicine, Integer> medicineAllocated) {
        StringBuilder stringBuilder = new StringBuilder();
        int quantity;
        int pricePerUnit;
        int totalPriceForSpecificMedicine;
        String medicineName;
        for (Map.Entry<Medicine, Integer> entry : medicineAllocated.entrySet()) {
            Medicine medicine = entry.getKey();
            medicineName = medicine.getMedicineName().toString();
            quantity = entry.getValue();
            pricePerUnit = Integer.parseInt(medicine.getPricePerUnit().toString());
            totalPriceForSpecificMedicine = pricePerUnit * quantity;
            totalPrice += totalPriceForSpecificMedicine;

            stringBuilder.append("<tr><td>")
                    .append(medicineName)
                    .append(HTML_TABLE_FORMATTING)
                    .append(quantity)
                    .append(HTML_TABLE_FORMATTING)
                    .append(pricePerUnit)
                    .append(HTML_TABLE_FORMATTING)
                    .append(totalPriceForSpecificMedicine)
                    .append("</td></tr>");
        }
        return stringBuilder.toString();
    }
}