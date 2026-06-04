package homework_6.exception.validate_email;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String message) {
        super(message);
    }
}
