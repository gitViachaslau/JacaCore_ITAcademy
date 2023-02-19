package by.academy.task_9.exceptions;

public class WordValueException extends Exception {
    public WordValueException() {
    }

    public WordValueException(String message) {
        super(message);
    }

    public WordValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public WordValueException(Throwable cause) {
        super(cause);
    }

    public WordValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
