package seedu.address.model.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import seedu.address.MainApp;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.IcNumber;
import seedu.address.model.person.Name;

/**
 * This interface bounds all classes implementing it to provide an implementation for generating a document,
 */
public class Document {

    //Formatting the path to the directory all documents should be saved in
    private static final int FILENAME_INITIAL_SLICING = 6;
    private static final int FILENAME_END_SLICING = 22;
    private static final String DUMMY_PATH = "/view/Documents/DocumentTemplate.html";
    private static final String COMPLETE_TEMPLATE_NAME = MainApp.class.getResource(DUMMY_PATH).toExternalForm()
            .substring(FILENAME_INITIAL_SLICING).replace("out/production", "src/main");
    private static final String DIRECTORY_PATH = COMPLETE_TEMPLATE_NAME
            .substring(0, COMPLETE_TEMPLATE_NAME.length() - FILENAME_END_SLICING);

    private static final String TEMPLATE_LOCATE_FAILURE_ERROR_MESSAGE =
            "Unable to find DocumentTemplate.html to use as template!";
    private static final String FILE_WRITE_FAILURE_ERROR_MESSAGE =
            "Unable to write contents into ";

    //Data placeholders in the HTML template from which all the document objects are extended from
    private static final String HEADER_PLACEHOLDER = "$headers";
    private static final String NAME_PLACEHOLDER = "$name";
    private static final String ICNUMBER_PLACEHOLDER = "$icNumber";
    private static final String CONTENT_PLACEHOLDER = "$content";

    private String filePath;
    private String fileName;
    private String fileType;
    private Name name;
    private IcNumber icNumber;
    private Map<Medicine, Integer> medicineAllocated;

    /**
     * Method that calls the various methods that help in the generation of the HTML file
     * for the document.
     * This includes a method to make the file name, another method to update the HTML template
     * with the correct values specified by the object that extends the document and lastly the
     * actual writing of the bytes into a file.
     */
    public void generateDocument() {
        makeFileName();
        makeFile(writeContentsIntoDocument());
    }

    /**
     * Formats the file name of the object that extends document.
     * */
    private void makeFileName() {
        fileName = fileType + "_For_" + name.toString().replaceAll("\\s", "")
                + "_" + icNumber.toString();
        filePath = DIRECTORY_PATH + File.separator + fileName + ".html";
    }

    /**
     * Generates the relevant header information that is on the printout of all the Documents
     * and formats them neatly.
     * @return neatly formatted headers, with general information of the document and the clinic.
     */
    private String generateHeaders() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return fileType + "<br>Issued On: " + formatter.format(date);
    }

    /**
     * Writing contents of the document into the HTML file.
     * @return HTML code that has the "fillers" filled up with the appropriate values.
     * For example, $headers in the HTML file is now replaced with the actual header values.
     * */
    private String writeContentsIntoDocument() {
        String htmlContent = convertHtmlIntoString();
        String title = fileType + " for " + this.name;
        htmlContent = htmlContent.replace("$title", title);
        HashMap<String, String> fieldValues = this.generateContent();
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            htmlContent = htmlContent.replace(key, value);
        }
        return htmlContent;
    }

    /**
     * Fills in the information as required by the fields of the document.
     * @return returns a HashMap that maps all the fields to their own correct value.
     */
    private HashMap<String, String> generateContent() {
        HashMap<String, String> informationFieldPairs = new HashMap<>();
        informationFieldPairs.put(HEADER_PLACEHOLDER, generateHeaders());
        informationFieldPairs.put(NAME_PLACEHOLDER, name.toString());
        informationFieldPairs.put(ICNUMBER_PLACEHOLDER, icNumber.toString());
        if (this instanceof Receipt) {
            informationFieldPairs.put(CONTENT_PLACEHOLDER, ((Receipt) this).formatReceiptInformation());
        } else {
            informationFieldPairs.put(CONTENT_PLACEHOLDER, "Lorem ipsum dolor sit amet");
        }
        return informationFieldPairs;
    }

    /**
     * The actual generation of the file representing the document using the updated HTML code.
     */
    private void makeFile(String htmlContent) {
        File newDocument = new File(filePath);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(newDocument);
            fileWriter.write(htmlContent);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(FILE_WRITE_FAILURE_ERROR_MESSAGE + fileName + "!");
        }
    }

    /**
     * Converting the template HTML into a string for modifications.
     * @return a string containing the template HTML code into a string for population of
     *          necessary fields required by the type of document.
     * */
    private String convertHtmlIntoString() {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(COMPLETE_TEMPLATE_NAME));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str).append("\n");
            }
            in.close();
        } catch (IOException e) {
            System.out.println(TEMPLATE_LOCATE_FAILURE_ERROR_MESSAGE);
        }
        return contentBuilder.toString();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setIcNumber(IcNumber icNumber) {
        this.icNumber = icNumber;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setMedicineAllocated(Map allocation) {
        this.medicineAllocated = allocation;
    }
}
