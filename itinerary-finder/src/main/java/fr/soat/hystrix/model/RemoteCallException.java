package fr.soat.hystrix.model;

public class RemoteCallException extends RuntimeException {

    private final ExceptionType exceptionType;
    private final int callDuration;

    public RemoteCallException(ExceptionType exceptionType, int callDuration, Throwable cause) {
        super(cause);
        this.exceptionType = exceptionType;
        this.callDuration = callDuration;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public enum ExceptionType {

        TIMEOUT,

        HYSTRIX_OPEN_CIRCUIT,

        HYSTRIX_REJECTED_THREAD_EXECUTION,

        OTHER;
    }

}
