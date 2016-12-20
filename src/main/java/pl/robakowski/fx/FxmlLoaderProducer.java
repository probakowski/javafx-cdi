package pl.robakowski.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FxmlLoaderProducer {
    @Inject
    Instance<Object> instance;

    @Produces
    @Dependent
    @Source("")
    public FXMLLoader createLoader(ResourceBundle resourceBundle, InjectionPoint injectionPoint) {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(resourceBundle);
        String source = injectionPoint.getAnnotated().getAnnotation(Source.class).value();
        URL location = injectionPoint.getMember().getDeclaringClass().getClassLoader().getResource(source);
        loader.setLocation(location);
        loader.setControllerFactory(param -> instance.select(param).get());
        return loader;
    }

    @Produces
    @Dependent
    @Source("")
    public Parent getParent(ResourceBundle resourceBundle, InjectionPoint injectionPoint) throws IOException {
        return createLoader(resourceBundle, injectionPoint).load();
    }
}
