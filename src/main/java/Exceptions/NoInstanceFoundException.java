package Exceptions;

public class NoInstanceFoundException extends Exception {
    public NoInstanceFoundException() {
        super("Not found instance");
    }
    public NoInstanceFoundException(String message) {
        super(message);
    }
}
