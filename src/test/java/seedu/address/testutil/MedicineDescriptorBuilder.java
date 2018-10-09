package seedu.address.testutil;

import seedu.address.logic.commands.EditMedicineCommand.MedicineDescriptor;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.medicine.MedicineName;
import seedu.address.model.medicine.MinimumStockQuantity;
import seedu.address.model.medicine.PricePerUnit;
import seedu.address.model.medicine.SerialNumber;
import seedu.address.model.medicine.Stock;

/**
 * A utility class to help with building Medicine Objects
 */
public class MedicineDescriptorBuilder {

    private MedicineDescriptor descriptor;

    public MedicineDescriptorBuilder() {
        descriptor = new MedicineDescriptor();
    }

    public MedicineDescriptorBuilder(MedicineDescriptor descriptor) {
        this.descriptor = new MedicineDescriptor(descriptor);
    }

    /**
     * Returns an {@code MedicineDescriptor} with fields containing {@code medicine}'s details
     */
    public MedicineDescriptorBuilder(Medicine medicine) {
        descriptor = new MedicineDescriptor();
        descriptor.setMedicineName(medicine.getMedicineName());
        descriptor.setMinimumStockQuantity(medicine.getMinimumStockQuantity());
        descriptor.setPricePerUnit(medicine.getPricePerUnit());
        descriptor.setSerialNumber(medicine.getSerialNumber());
        descriptor.setStock(medicine.getStock());
    }

    /**
     * Sets the {@code MedicineName} of the {@code MedicineDescriptor} that we are building.
     */
    public MedicineDescriptorBuilder withMedicineName(String medicineName) {
        descriptor.setMedicineName(new MedicineName(medicineName));
        return this;
    }

    /**
     * Sets the {@code MinimumStockQuantity} of the {@code MedicineDescriptor} that we are building.
     */
    public MedicineDescriptorBuilder withMinimumStockQuantity(String minimumStockQuantity) {
        descriptor.setMinimumStockQuantity(new MinimumStockQuantity(minimumStockQuantity));
        return this;
    }

    /**
     * Sets the {@code PricePerUnit} of the {@code MedicineDescriptor} that we are building.
     */
    public MedicineDescriptorBuilder withPricePerUnit(String pricePerUnit) {
        descriptor.setPricePerUnit(new PricePerUnit(pricePerUnit));
        return this;
    }

    /**
     * Sets the {@code SerialNumber} of the {@code MedicineDescriptor} that we are building.
     */
    public MedicineDescriptorBuilder withSerialNumber(String serialNumber) {
        descriptor.setSerialNumber(new SerialNumber(serialNumber));
        return this;
    }

    /**
     * Sets the {@code Stock} of the {@code MedicineDescriptor} that we are building.
     */
    public MedicineDescriptorBuilder withStock(String stock) {
        descriptor.setStock(new Stock(stock));
        return this;
    }

    public MedicineDescriptor build() {
        return descriptor;
    }
}
