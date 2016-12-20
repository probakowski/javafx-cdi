package pl.robakowski.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import org.controlsfx.control.StatusBar;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Locale;

@Singleton
public class MainController {

    @Inject
    private Event<Locale> event;

    @FXML
    private TabPane mainTab;

    @FXML
    private StatusBar statusBar;

    @FXML
    private TabPane ribbon;

    @FXML
    private ChoiceBox<Locale> language;

    @FXML
    void onListClick(ActionEvent $) {
    }

    @Produces
    @Main
    public TabPane getMainTab() {
        return mainTab;
    }

    @Produces
    @Ribbon
    public TabPane getRibbon() {
        return ribbon;
    }

    @FXML
    public void initialize() {
        ObservableList<Locale> items = language.getItems();
        items.add(Locale.US);
        items.add(Locale.GERMANY);
        language.setValue(Locale.US);
        language.getSelectionModel().selectedItemProperty().addListener(($, $$, locale) -> {
            language.setValue(locale);
            event.fire(language.getValue());
        });
    }
}

