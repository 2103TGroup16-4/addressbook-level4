package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Medicine> PREDICATE_SHOW_ALL_MEDICINES = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPerson(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePerson(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPerson(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the address book.
     */
    void updatePerson(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPersonList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Patient> predicate);

    /** Returns an unmodifiable view of the filtered medicine list */
    ObservableList<Medicine> getFilteredMedicineList();

    /**
     * Updates the filter of the filtered medicine list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMedicineList(Predicate<Medicine> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Returns true if a medicine {@code medicine} exists in the records.
     */
    boolean hasMedicine(Medicine medicine);

    /**
     * Adds the given medicine.
     * {@code medicine} must not already exist in the records.
     */
    void addMedicine(Medicine medicine);

    /**
     * Deletes the given medicine.
     * The medicine must exist in the records.
     */
    void deleteMedicine(Medicine medicine);

    /**
     * Replaces the given medicine {@code target} with {@code editedMedicine}.
     * {@code target} must exist in the address book.
     * The medicine identity of {@code editedMedicine} must not be the same as another
     * existing medicine in the address book.
     */
    void updateMedicine(Medicine target, Medicine editedMedicine);
}
