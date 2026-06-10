package practice_7_exception.library.exceptions;

// непроверяемый -> RuntimeException
public class InvalidBookException extends RuntimeException {
    public InvalidBookException(String message) {
        super(message);
    }

}
