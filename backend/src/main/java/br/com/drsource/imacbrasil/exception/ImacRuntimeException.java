package br.com.drsource.imacbrasil.exception;

public class ImacRuntimeException extends RuntimeException{

    public ImacRuntimeException() {
    }

    public ImacRuntimeException(String message) {
        super(message);
    }

    public ImacRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImacRuntimeException(Throwable cause) {
        super(cause);
    }

    public ImacRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
