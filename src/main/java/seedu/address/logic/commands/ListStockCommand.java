package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEDICINES;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;

/**
 * Lists all medicines in the records to the user.
 */
public class ListStockCommand extends Command {

    public static final String COMMAND_WORD = "listStock";

    public static final String MESSAGE_SUCCESS = "Listed all stock.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredMedicineList(PREDICATE_SHOW_ALL_MEDICINES);
        // Temporary to print out to console. Should replace this with GUI in the future!!!
        ObservableList<Medicine> medicineObservableList = model.getFilteredMedicineList();
        StringBuilder medicineListStringBuilder = new StringBuilder();
        medicineObservableList.forEach((medicine) -> {
            medicineListStringBuilder.append(medicine.toString());
            medicineListStringBuilder.append("\n");
        });

        return new CommandResult(medicineListStringBuilder.toString());
    }
}
