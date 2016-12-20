package pl.robakowski.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.util.AnnotationLiteral;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WeldContainer weld = new Weld().initialize();

        weld.select(ApplicationParametersProducer.class).get().setArguments(getParameters());

        weld.event().select(Stage.class, new AnnotationLiteral<Main>() {
        }).fire(primaryStage);
    }
}
