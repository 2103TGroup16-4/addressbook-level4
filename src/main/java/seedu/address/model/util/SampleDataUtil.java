package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.medicine.MedicineName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IcNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.medicalrecord.Disease;
import seedu.address.model.person.medicalrecord.DrugAllergy;
import seedu.address.model.person.medicalrecord.Note;
import seedu.address.model.person.medicalrecord.Quantity;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePersons() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new IcNumber("S1234567X"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Patient(new Name("Bernice Yu"), new IcNumber("S1234567X"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new IcNumber("S1234567X"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Patient(new Name("David Li"), new IcNumber("S1234567X"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new IcNumber("S1234567X"), new Phone("92492021"),
                    new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new IcNumber("S1234567X"), new Phone("92624417"),
                    new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePersons()) {
            sampleAb.addPerson(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a drug allergy list containing the list of strings given.
     */
    public static List<DrugAllergy> getDrugAllergyList(String... strings) {
        return Arrays.stream(strings)
                .map(DrugAllergy::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a disease history list containing the list of strings given.
     */
    public static List<Disease> getDiseaseList(String... strings) {
        return Arrays.stream(strings)
                .map(Disease::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a note list containing the list of strings given.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                .map(Note::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a map of SerialNumber to Quantity containing the list of pairs given.
     */
    public static Map<MedicineName, Quantity> getDispensedMedicines(Map.Entry<String, Integer>... entries) {
        Map<MedicineName, Quantity> dispensedMedicines = new HashMap<>();
        Arrays.stream(entries).forEach((entry) -> {
            MedicineName medicineName = new MedicineName(entry.getKey());
            Quantity quantity = new Quantity(entry.getValue().toString());
            dispensedMedicines.put(medicineName, quantity);
        });
        return dispensedMedicines;
    }

}
