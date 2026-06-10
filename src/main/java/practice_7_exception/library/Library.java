package practice_7_exception.library;

import practice_7_exception.library.exceptions.BookNotFoundException;
import practice_7_exception.library.exceptions.InvalidBookException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    // добавлять книгу
    // Обработка исключения: если мы добавляем существующую книгу,
    // то мы получаем непроверяемое исключение InvalidBookException
    public void addBook(Book book) throws InvalidBookException {
        if (books.contains(book)) {
            throw new IndexOutOfBoundsException("Такая книга уже существует в библиотеке!");
        } else {
            books.add(book);
        }
    }

    // искать книгу
    // Обработка исключения: если мы не можем найти книгу,
    // то случится обработка проверяемого исключения BookNotFoundException
    public Book findBook(String name) throws BookNotFoundException {
        AtomicReference<Book> foundBook = new AtomicReference<>();
        books.forEach(book -> {
            if (book.getName() == name) {
                foundBook.set(book);
            }
        });

        if (foundBook.get() == null) {
            throw new BookNotFoundException("Не нашли книгу по имени " + name);
        }
        return foundBook.get();
    }
}
