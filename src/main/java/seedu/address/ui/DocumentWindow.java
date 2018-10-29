package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.document.Document;

/**
 * UI window representing a webview displaying the document generated for Patients.
 */
public class DocumentWindow extends UiPart<Stage> {

    public static final String DOCUMENT_TEMPLATE_FILE_PATH = "/view/Documents/DocumentTemplate.html";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "DocumentWindow.fxml";

    private static String documentTemplateUrl;

    private static int counter = 0;

    @FXML
    private WebView browser;

    /**
     * Creates a new DocumentWindow.
     *
     * @param root Stage to use as the root of the DocumentWindow.
     */
    public DocumentWindow(Stage root) {
        super(FXML, root);
        this.documentTemplateUrl = getClass().getResource(DOCUMENT_TEMPLATE_FILE_PATH).toExternalForm();
        this.load();
    }

    /**
     * Creates a new DocumentWindow.
     */
    public DocumentWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */

    public void show(Document document) {
        logger.fine("Showing document screenshot");
        runScript(getScript(document), counter);
        Platform.runLater(() -> browser.getEngine().load(documentTemplateUrl));
        getRoot().show();
    }

    private void load() {
        browser.getEngine().load(documentTemplateUrl);
    }

    /**
     * Default show command for testing.
     */
    public void showTest() {
        logger.fine("Showing default document");
        getRoot().show();
    }

    private String getScript(Document document) {
        String script = "loadDetails('";
        script += document.getFileName();
        script += "', '";
        script += document.getHeaders();
        script += "', '";
        script += document.getPatientName();
        script += "', '";
        script += document.getPatientIc();
        script += "', '";
        script += document.getContent();
        script += "');";
        return script;
    }

    /**
     * This function runs the executes some javascript in the html file.
     * @param script script to run
     * @param scriptCounter Ensure that only the script called is ran using an index counter.
     */
    private void runScript(String script, int scriptCounter) {
        browser.getEngine().getLoadWorker().stateProperty().addListener((
                ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
            if (newValue == Worker.State.SUCCEEDED && counter == scriptCounter) {
                Platform.runLater(() -> browser.getEngine().executeScript(script));
                counter++;
            }
        });
    }

    /**
     * Returns true if the receipt window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the receipt window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
