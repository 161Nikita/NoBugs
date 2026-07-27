package homework_mock.task_oop.repeat;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;
    private List<Book> bookList = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    // добавить книгу в библиотеку
    public void addBook(Book book) {
        this.bookList.add(book);
    }

    // выдать книгу читателю
    public void lendBook(Book book) {
        for (Book b : bookList) {
            if (b.getTitle().equals(book.getTitle()) && b.getStatus() == BookStatus.AVAILABLE) {
                b.setStatus(BookStatus.LEND);
            }
        }
    }

    // вернуть книгу обратно
    public void returnBook(Book book) {
        for (Book b : bookList) {
            if (b.getTitle().equals((book.getTitle())) && b.getStatus() == BookStatus.LEND) {
                b.setStatus(BookStatus.AVAILABLE);
            }
        }
    }

    //показать список доступных книг
    public void getListBook() {

        for (Book b : bookList) {
            if (b.getStatus() == BookStatus.AVAILABLE) {
                System.out.println(b);
            }
        }
    }
}
