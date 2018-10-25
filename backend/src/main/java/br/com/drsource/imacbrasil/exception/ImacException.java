package br.com.drsource.imacbrasil.exception;

public class ImacException extends Exception {

    public ImacException() {
    }

    public ImacException(String message) {
        super(message);
    }

    public ImacException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImacException(Throwable cause) {
        super(cause);
    }

    public ImacException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
