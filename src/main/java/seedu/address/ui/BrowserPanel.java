package seedu.address.ui;

import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Patient;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String SEARCH_PAGE_URL =
            "https://se-edu.github.io/addressbook-level4/DummySearchPage.html?name=";
    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView browser;

    @FXML
    private TreeView treeView;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    /**
     * Loads the page for given patient
     * @param patient patient to load
     */
    private void loadPersonPage(Patient patient) {
        // loadPage(SEARCH_PAGE_URL + patient.getName().fullName);
        String filePath = "/view/PatientView.html";
        String url = MainApp.class.getResource(filePath).toExternalForm();
        url = addPatientDetailsAsArgs(patient, url);
        loadPage(url);
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection());
    }

    /**
     * Parses patient details from patient object and appends to URL as html parameters.
     * @param patient Patient details to parse
     * @param url filepath
     * @return Final url with args
     */
    private String addPatientDetailsAsArgs(Patient patient, String url) {

        url += "?name=";
        url += patient.getName().fullName;

        url += "&ic=";
        url += patient.getIcNumber().value;

        url += "&address=";
        url += patient.getAddress().value;

        url += "&phone=";
        url += patient.getPhone().value;

        url += "&email=";
        url += patient.getEmail().value;

        url += "&blood=";
        url += patient.getMedicalRecord().getBloodType().value;

        url += "&diseases=";
        url += convertListToString(patient.getMedicalRecord().getDiseaseHistory());

        url += "&drugs=";
        url += convertListToString(patient.getMedicalRecord().getDrugAllergies());

        url += "&notes=";
        url += convertListToString(patient.getMedicalRecord().getNotes());

        return url;
    }

    /**
     * Convert any list into a String with commas.
     * @param list list to convert
     * @param <T> Generic method
     * @return converted list as String
     */
    private <T> String convertListToString(List<T> list) {
        String result = "";
        for (T item: list) {
            result += item.toString();
            result += ", ";
        }
        return result.length() >= 2 ? result.substring(0, result.length() - 2) : result;
    }
}
