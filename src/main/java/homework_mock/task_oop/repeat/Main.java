package homework_mock.task_oop.repeat;

/*
В системе есть библиотека.
У библиотеки есть:
название
список книг
У каждой книги есть:
название
автор

Нужно реализовать систему, которая может:

добавить книгу в библиотеку
выдать книгу читателю
вернуть книгу обратно
показать список доступных книг

Правила:

книгу можно выдать, если она доступна
при выдаче статус меняется на LEND
при возврате статус меняется AVAILABLE

Пример:

В библиотеке есть книги:
"1984" — Оруэлл — 2
"Мастер и Маргарита" — Булгаков — 1
После одной выдачи:
"1984" — 1
"Мастер и Маргарита" — 1
После возврата:
"1984" — 2
"Мастер и Маргарита" — 1
 */

public class Main {

    public static void main(String[] args) {

        Book book1 = new Book("1984", "Оруэлл");
        Book book2 = new Book("Мастер и Маргарита", "Булгаков");

        Library library = new Library("General");
        library.addBook(book1);
        library.addBook(book2);

        library.lendBook(book1);
        library.returnBook(book1);

        System.out.println("В библиотеке есть книги:");
        library.getListBook();

    }
}
