package fr.soat.hystrix.model;

public class RemoteCallException extends RuntimeException {

    private final ExceptionType exceptionType;

    public RemoteCallException(ExceptionType exceptionType, Throwable cause) {
        super(cause);
        this.exceptionType = exceptionType;

    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public enum ExceptionType {

        TIMEOUT,

        HYSTRIX_OPEN_CIRCUIT,

        HYSTRIX_REJECTED_THREAD_EXECUTION,

        OTHER;
    }

}
