package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.Patient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Medicine> filteredMedicines;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        filteredPatients = new FilteredList<>(versionedAddressBook.getPersonList());
        filteredMedicines = new FilteredList<>(versionedAddressBook.getMedicineList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        versionedAddressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(versionedAddressBook));
    }

    @Override
    public boolean hasPerson(Patient patient) {
        requireNonNull(patient);
        return versionedAddressBook.hasPerson(patient);
    }

    @Override
    public boolean hasMedicine(Medicine medicine) {
        requireNonNull(medicine);
        return versionedAddressBook.hasMedicine(medicine);
    }

    @Override
    public void deletePerson(Patient target) {
        versionedAddressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addPerson(Patient patient) {
        versionedAddressBook.addPerson(patient);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void addMedicine(Medicine medicine) {
        versionedAddressBook.addMedicine(medicine);
        updateFilteredMedicineList(PREDICATE_SHOW_ALL_MEDICINES);
        indicateAddressBookChanged();
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        versionedAddressBook.removeMedicine(medicine);
        indicateAddressBookChanged();
    }

    @Override
    public void updateMedicine(Medicine target, Medicine editedMedicine) {
        requireAllNonNull(target, editedMedicine);

        versionedAddressBook.updateMedicine(target, editedMedicine);

    }

    @Override
    public void updatePerson(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        versionedAddressBook.updatePerson(target, editedPatient);
        indicateAddressBookChanged();
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredPatients);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Filtered Medicine List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Medicine} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Medicine> getFilteredMedicineList() {
        return FXCollections.unmodifiableObservableList(filteredMedicines);
    }

    @Override
    public void updateFilteredMedicineList(Predicate<Medicine> predicate) {
        requireNonNull(predicate);
        filteredMedicines.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && filteredPatients.equals(other.filteredPatients)
                && filteredMedicines.equals(other.filteredMedicines);
    }

}
