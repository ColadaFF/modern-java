package co.com.ias.deved.workshop.util;

public class NonFatalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NonFatalException(Throwable cause) {
        super(cause);
    }

}