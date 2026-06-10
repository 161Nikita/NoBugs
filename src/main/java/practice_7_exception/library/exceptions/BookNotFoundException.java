package practice_7_exception.library.exceptions;

// проверяемое -> Exception (наследников)
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
