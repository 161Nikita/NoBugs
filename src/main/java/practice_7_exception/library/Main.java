package practice_7_exception.library;

import practice_7_exception.library.exceptions.BookNotFoundException;

public class Main {
    public static void main(String[] args) {

       Library library = new Library();

       library.addBook(new Book("Java", "Nikita Krapivin"));
       library.addBook(new Book("Onegin", "Pushkin"));
       library.addBook(new Book("Lord of the Rings", "Megabook"));
       //library.addBook(new Book("Lord of the Rings", "Megabook"));

        try {
            library.findBook("344");
        } catch (BookNotFoundException e) {
            System.out.println("Поймали исключение!!!");
        }
    }
}
