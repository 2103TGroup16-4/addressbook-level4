/* @@author 99percentile */
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEDICINES;

import javafx.collections.ObservableList;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ShowMedicineListEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;

public class CheckStockCommand extends Command {

    public static final String COMMAND_WORD = "checkStock";
    public static final String MESSAGE_SUCCESS = "The following stocks are low in supply";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMedicineList(PREDICATE_SHOW_ALL_MEDICINES);
        ObservableList<Medicine> medicineObservableList = model.getFilteredMedicineList();
        StringBuilder medicineListStringBuilder = new StringBuilder();
        medicineObservableList.filtered(medicine ->
                medicine.getStockValue() < medicine.getMsqValue())
            .forEach((medicine) -> {
            medicineListStringBuilder.append(medicine.toString());
            medicineListStringBuilder.append("\n");
        });

        EventsCenter.getInstance().post(new ShowMedicineListEvent());

        return new CommandResult(medicineListStringBuilder.toString());
    };
}
