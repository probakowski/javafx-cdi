package pl.robakowski.fx;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ResourceBundle;

@Singleton
public class MainStageLoader {

    private Stage stage;

    public void onPrimaryStageCreated(@Observes @Main Stage stage, @Translated("title") String title, @Source
            ("fxml/main.fxml") Parent parent) throws
            IOException {
        this.stage = stage;
        stage.setScene(new Scene(parent));
        stage.setTitle(title);
        stage.show();
    }

    public void onLocaleChange(@Observes ResourceBundle locale, @Translated("title") String title, @Source
            ("fxml/main.fxml") Parent parent) throws IOException {
        onPrimaryStageCreated(this.stage, title, parent);
    }
}
