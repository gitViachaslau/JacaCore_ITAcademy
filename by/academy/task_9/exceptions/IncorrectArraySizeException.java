package by.academy.task_9.exceptions;

public class IncorrectArraySizeException extends Exception{
    public IncorrectArraySizeException() {
    }

    public IncorrectArraySizeException(String message) {
        super(message);
    }

    public IncorrectArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectArraySizeException(Throwable cause) {
        super(cause);
    }

    public IncorrectArraySizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
