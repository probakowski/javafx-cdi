package pl.robakowski.fx;

import javafx.application.Application;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ApplicationParametersProducer {
    private Application.Parameters args;

    public void setArguments(Application.Parameters args) {
        this.args = args;
    }

    @Produces
    public Application.Parameters getArguments() {
        return args;
    }
}
