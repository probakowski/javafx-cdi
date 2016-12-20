package pl.robakowski.fx;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Singleton
public class ResourceBundleProducer {
    private Locale locale = Locale.US;

    private ResourceBundle resourceBundle;

    public void onLocaleChange(@Observes Locale locale, Event<ResourceBundle> event) throws IOException {
        this.locale = locale;
        resourceBundle = null;
        getResourceBundle();
        event.fire(resourceBundle);
    }

    @Produces
    @Dependent
    public ResourceBundle getResourceBundle() throws IOException {
        if (resourceBundle == null) {
            InputStream properties = ResourceBundleProducer.class.getClassLoader().getResourceAsStream(
                    String.format("i18n/%s.properties", locale.toString()));
            resourceBundle = new PropertyResourceBundle(properties);
        }

        return resourceBundle;
    }
}
