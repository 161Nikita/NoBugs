package homework_mock.task_oop.library_8service;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String titleLibrary;
    private List<Book> bookList = new ArrayList<>();

    public Library(String titleLibrary) {
        this.titleLibrary = titleLibrary;
    }

    // добавить книгу в библиотеку
    public void addBook(Book book) {
        this.bookList.add(book);
    }

    // выдать книгу читателю
    public void lendBook(String title) {
        for (Book book : bookList) {
            if (book.getTitleBook().equalsIgnoreCase(title) && book.getStatusBook() == StatusBook.AVAILABLE) {
                book.setStatusBook(StatusBook.LEND);
                return;
            }
        }
    }


    // вернуть книгу обратно
    public void returnBook(String title) {
        for (Book book : bookList) {
            if (book.getTitleBook().equalsIgnoreCase(title) && book.getStatusBook() == StatusBook.LEND) {
                book.setStatusBook(StatusBook.AVAILABLE);
                return;
            }
        }
    }

    public void printAvailableBook() {
        String checked = "";
        for (Book book : bookList) {
            // Если книга доступна и мы её название еще не выводили на экран
            if (book.getStatusBook() == StatusBook.AVAILABLE && !checked.contains(book.getTitleBook())) {
                int count = 0;

                // В одну строчку считаем, сколько таких же книг доступно
                for (Book b : bookList) if (b.getTitleBook().equals(book.getTitleBook()) && b.getStatusBook() == StatusBook.AVAILABLE) count++;

                // Печатаем строку строго по ТЗ
                System.out.println("\"" + book.getTitleBook() + "\" — " + book.getAuthor() + " — " + count);

                // Запоминаем, что это название мы уже напечатали
                checked += book.getTitleBook() + ",";
            }
        }
    }

    }

