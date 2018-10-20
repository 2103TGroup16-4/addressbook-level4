package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.document.ReferralLetter;
import seedu.address.model.Model;
import seedu.address.model.PatientQueue;
import seedu.address.model.ServedPatientList;
import seedu.address.model.person.CurrentPatient;
import seedu.address.model.person.ServedPatient;

//integrate select command
/**
 * Generates a referral letter for {@code Patient} specified by {@code index} that appears in the GUI and in a pdf.
 */
public class ReferralLetterCommand extends QueueCommand {
    public static final String COMMAND_WORD = "refer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates a referral letter for the patient in the specified"
            + " index. Includes information like name and NRIC of patient,"
            + " and information written by the issuing doctor. \n"
            + "Example: " + COMMAND_WORD + "<person's index>";

    public static final String MESSAGE_SUCCESS = "Referral letter generated for patient!";

    private final Index index;

    /**
     * Creates a ReferralLetterCommand for the {@code servedPatient} specified by {@code index}
     */
    public ReferralLetterCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, PatientQueue patientQueue, CurrentPatient currentPatient,
                                 ServedPatientList servedPatientList, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= servedPatientList.getServedPatientListLength()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        final ReferralLetter rl;

        ServedPatient servedPatient = servedPatientList.selectServedPatient(index);
        rl = new ReferralLetter(servedPatient);
        rl.generateDocument();

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReferralLetterCommand // instanceof handles nulls
                && index.equals(((ReferralLetterCommand) other).index)); // state check
    }
}
