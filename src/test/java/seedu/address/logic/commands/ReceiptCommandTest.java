package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import static seedu.address.logic.commands.QueueCommandTestUtil.assertUniqueFileInFilteredFileList;
import static seedu.address.logic.commands.QueueCommandTestUtil.fileCleanUp;
import static seedu.address.logic.commands.QueueCommandTestUtil.generateFileName;
import static seedu.address.logic.commands.QueueCommandTestUtil.generateServedPatientList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientQueue;
import seedu.address.model.PatientQueueManager;
import seedu.address.model.ServedPatientList;
import seedu.address.model.ServedPatientListManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CurrentPatient;
import seedu.address.testutil.TypicalPersons;

public class ReceiptCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private PatientQueue patientQueue;
    private CurrentPatient currentPatient;
    private ServedPatientList servedPatientList;
    private CommandHistory commandHistory;

    @Before
    public void setUp() {
        patientQueue = new PatientQueueManager();
        currentPatient = new CurrentPatient();
        servedPatientList = new ServedPatientListManager();
        commandHistory = new CommandHistory();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validServedPatient_ReceiptSuccess() throws Exception {
        servedPatientList = generateServedPatientList(TypicalPersons.ALICE, TypicalPersons.BOB);
        String expectedMessage = ReceiptCommand.MESSAGE_GENERATE_RECEIPT_SUCCESS;
        ReceiptCommand receiptCommand = new ReceiptCommand(INDEX_FIRST_PERSON);
        CommandResult commandResult = receiptCommand.execute(model, patientQueue,
                currentPatient, servedPatientList, commandHistory);
        File file = receiptCommand.getReceipt().getFile();
        fileCleanUp(file);
        assertEquals(expectedMessage, commandResult.feedbackToUser);
    }

    @Test
    public void execute_ServedPatientListEmpty_commandExceptionThrown() throws Exception {
        thrown.expect(CommandException.class);
        servedPatientList = generateServedPatientList();
        new ReceiptCommand(INDEX_FIRST_PERSON).execute(model, patientQueue,
                currentPatient, servedPatientList, commandHistory);
    }

    @Test
    public void execute_receiptFileName_Success() throws Exception {
        servedPatientList = generateServedPatientList(TypicalPersons.ALICE, TypicalPersons.BOB);
        ReceiptCommand receiptCommand = new ReceiptCommand(INDEX_FIRST_PERSON);
        receiptCommand.execute(model, patientQueue, currentPatient, servedPatientList, commandHistory);
        String fileType = receiptCommand.getReceipt().FILE_TYPE;
        String fileName = generateFileName(fileType, TypicalPersons.ALICE);
        assertUniqueFileInFilteredFileList(fileName);
    }
}

