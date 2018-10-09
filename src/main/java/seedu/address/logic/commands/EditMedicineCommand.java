package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINIMUM_STOCK_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_PER_UNIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOCK;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.medicine.MedicineName;
import seedu.address.model.medicine.MinimumStockQuantity;
import seedu.address.model.medicine.PricePerUnit;
import seedu.address.model.medicine.SerialNumber;
import seedu.address.model.medicine.Stock;

/**
 * Edits the details of an existing medicine in the records.
 */
public class EditMedicineCommand extends Command {

    public static final String COMMAND_WORD = "editMedicine";

    public static final String MESSAGE_USSAGE = COMMAND_WORD + ": Edits the details of the medicine identified "
            + "by the index number used in the displayed medicine list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MEDICINE_NAME + "MEDICINE NAME] "
            + "[" + PREFIX_MINIMUM_STOCK_QUANTITY + "MINIMUM STOCK QUANTITY] "
            + "[" + PREFIX_PRICE_PER_UNIT + "PRICE PER UNIT] "
            + "[" + PREFIX_SERIAL_NUMBER + "SERIAL NUMBER] "
            + "[" + PREFIX_STOCK + "STOCK   ]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MINIMUM_STOCK_QUANTITY + "100 "
            + PREFIX_STOCK + "2000";

    public static final String MESSAGE_EDIT_MEDICINE_SUCCESS = "Edited Medicine: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEDICINE = "This medicine already exists in the address book.";

    private final Index index;
    private final MedicineDescriptor medicineDescriptor;

    /**
     * @param index of the medicine in the filtered medicine list to edit
     * @param medicineDescriptor details to edit the medicine with
     */
    public EditMedicineCommand(Index index, MedicineDescriptor medicineDescriptor) {
        requireNonNull(index);
        requireNonNull(medicineDescriptor);

        this.index = index;
        this.medicineDescriptor = medicineDescriptor;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Medicine> lastShownList = model.getFilteredMedicineList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICINE_DISPLAYED_INDEX);
        }

        Medicine medicineToEdit = lastShownList.get(index.getZeroBased());
        Medicine editedMedicine = createEditedMedicine(medicineToEdit, medicineDescriptor);

        if (!medicineToEdit.isSameMedicine(editedMedicine) && model.hasMedicine(editedMedicine)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEDICINE);
        }

        model.updateMedicine(medicineToEdit, editedMedicine);
        model.updateFilteredMedicineList(Model.PREDICATE_SHOW_ALL_MEDICINES);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_MEDICINE_SUCCESS, editedMedicine));
    }

    /**
     * Creates and returns a {@code Medicine} with the details of {@code medicineToEdit}
     * edited with {@code MedicineDescriptor}.
     */
    private static Medicine createEditedMedicine(Medicine medicineToEdit, MedicineDescriptor medicineDescriptor) {
        assert medicineToEdit != null;

        MedicineName updatedMedicineName = medicineDescriptor.getMedicineName()
                .orElse(medicineToEdit.getMedicineName());
        MinimumStockQuantity updatedMinimumStockQuantity = medicineDescriptor.getMinimumStockQuantity()
                .orElse(medicineToEdit.getMinimumStockQuantity());
        PricePerUnit updatedPricePerUnit = medicineDescriptor.getPricePerUnit()
                .orElse(medicineToEdit.getPricePerUnit());
        SerialNumber updatedSerialNumber = medicineDescriptor.getSerialNumber()
                .orElse(medicineToEdit.getSerialNumber());
        Stock updatedStock = medicineDescriptor.getStock()
                .orElse(medicineToEdit.getStock());

        return new Medicine(updatedMedicineName, updatedMinimumStockQuantity,
                updatedPricePerUnit, updatedSerialNumber, updatedStock);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditMedicineCommand e = (EditMedicineCommand) other;
        return index.equals(e.index)
                && medicineDescriptor.equals(e.medicineDescriptor);
    }

    /**
     * Stores the details to edit the medicine with. Each non-empty field value will replace the
     * corresponding field value of the medicine.
     */
    public static class MedicineDescriptor {
        private MedicineName medicineName;
        private MinimumStockQuantity minimumStockQuantity;
        private PricePerUnit pricePerUnit;
        private SerialNumber serialNumber;
        private Stock stock;

        public MedicineDescriptor() {}

        /**
         * Copy constructor.
         */
        public MedicineDescriptor(MedicineDescriptor toCopy) {
            setMedicineName(toCopy.medicineName);
            setMinimumStockQuantity(toCopy.minimumStockQuantity);
            setPricePerUnit(toCopy.pricePerUnit);
            setSerialNumber(toCopy.serialNumber);
            setStock(toCopy.stock);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(medicineName, minimumStockQuantity, pricePerUnit, serialNumber, stock);
        }

        public void setMedicineName(MedicineName medicineName) {
            this.medicineName = medicineName;
        }

        public Optional<MedicineName> getMedicineName() {
            return Optional.ofNullable(medicineName);
        }

        public void setMinimumStockQuantity(MinimumStockQuantity minimumStockQuantity) {
            this.minimumStockQuantity = minimumStockQuantity;
        }

        public Optional<MinimumStockQuantity> getMinimumStockQuantity() {
            return Optional.ofNullable(minimumStockQuantity);
        }

        public void setPricePerUnit(PricePerUnit pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
        }

        public Optional<PricePerUnit> getPricePerUnit() {
            return Optional.ofNullable(pricePerUnit);
        }

        public void setSerialNumber(SerialNumber serialNumber) {
            this.serialNumber = serialNumber;
        }

        public Optional<SerialNumber> getSerialNumber() {
            return Optional.ofNullable(serialNumber);
        }

        public void setStock(Stock stock) {
            this.stock = stock;
        }

        public Optional<Stock> getStock() {
            return Optional.ofNullable(stock);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof MedicineDescriptor)) {
                return false;
            }

            // state check
            MedicineDescriptor e = (MedicineDescriptor) other;

            return getMedicineName().equals(e.getMedicineName())
                    && getMinimumStockQuantity().equals(e.getMinimumStockQuantity())
                    && getPricePerUnit().equals(e.getPricePerUnit())
                    && getSerialNumber().equals(e.getSerialNumber())
                    && getStock().equals(e.getStock());
        }
    }
}
