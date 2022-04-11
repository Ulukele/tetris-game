package exceptions;

public class LoadConfigurationException extends Exception {
    public LoadConfigurationException() {
        super("Unable to load configuration");
    }

    public LoadConfigurationException(String message) {
        super(message);
    }
}
