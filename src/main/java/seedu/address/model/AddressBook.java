package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.medicine.UniqueMedicineList;
import seedu.address.model.person.Patient;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueMedicineList medicines;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        medicines = new UniqueMedicineList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPersons(List<Patient> patients) {
        this.persons.setPersons(patients);
    }

    /**
     * Replaces the contents of the medicine list with {@code medicines}.
     * {@code medicines} must not contain duplicate medicines.
     */
    public void setMedicines(List<Medicine> medicines) {
        this.medicines.setMedicines(medicines);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setMedicines(newData.getMedicineList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    public boolean hasPerson(Patient patient) {
        requireNonNull(patient);
        return persons.contains(patient);
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the address book.
     */
    public void addPerson(Patient p) {
        persons.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another existing
     * patient in the address book.
     */
    public void updatePerson(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        persons.setPerson(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Patient key) {
        persons.remove(key);
    }

    //// medicine-level operations

    /**
     * Returns true if a medicine with the same identity as {@code medicine} exists in the records.
     */
    public boolean hasMedicine(Medicine medicine) {
        requireNonNull(medicine);
        return medicines.contains(medicine);
    }

    /**
     * Adds a medicine to the records.
     * The medicine must not already exist in the record.
     */
    public void addMedicine(Medicine m) {
        medicines.add(m);
    }

    /**
     * Removes {@code medicine} from this {@code AddressBook}.
     * {@code key} must exist in the records.
     */
    public void removeMedicine(Medicine medicine) {
        medicines.remove(medicine);
    }

    /**
     * Replaces the given medicine {@code target} with {@code editedMedicine}.
     * {@code target} must exist in the address book.
     * The medicine identity of {@code editedMedicine} must not be the same as another
     * existing medicine in the address book.
     */
    public void updateMedicine(Medicine target, Medicine editedMedicine) {
        requireNonNull(editedMedicine);

        medicines.setPerson(target, editedMedicine);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Patient> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Medicine> getMedicineList() {
        return medicines.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
