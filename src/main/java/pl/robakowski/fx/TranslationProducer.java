package pl.robakowski.fx;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.ResourceBundle;

public class TranslationProducer {

    @Produces
    @Translated("")
    public String getTranslation(InjectionPoint injectionPoint, ResourceBundle resourceBundle) {
        return resourceBundle.getString(injectionPoint.getAnnotated().getAnnotation(Translated.class).value());
    }
}
