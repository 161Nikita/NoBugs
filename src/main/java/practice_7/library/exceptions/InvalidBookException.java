package practice_7.library.exceptions;

// непроверяемый -> RuntimeException
public class InvalidBookException extends RuntimeException {
    public InvalidBookException(String message) {
        super(message);
    }

}
