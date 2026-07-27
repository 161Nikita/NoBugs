package homework_mock.task_oop.library_8service;

/*
"В системе есть библиотека.

У библиотеки есть: library
название
список книг

У каждой книги есть: Book
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
""1984"" — Оруэлл — 2
""Мастер и Маргарита"" — Булгаков — 1
После одной выдачи:
""1984"" — 1
""Мастер и Маргарита"" — 1

После возврата:
""1984"" — 2
""Мастер и Маргарита"" — 1"
 */

public class Main {

    public static void main(String[] args) {

        // Создаем ТРИ отдельных объекта книг (две "1984" и одну "Мастер и Маргарита")
        Book orual1 = new Book("1984", "Оруэлл");
        Book orual2 = new Book("1984", "Оруэлл");
        Book bulgakov = new Book("Мастер и Маргарита", "Булгаков");

        Library library = new Library("Главная");

        // Добавляем разные объекты на полку
        library.addBook(orual1);
        library.addBook(orual2);
        library.addBook(bulgakov);

        System.out.println("--- В библиотеке есть книги: ---");
        library.printAvailableBook();

        System.out.println("\n--- После одной выдачи: ---");
        library.lendBook("1984");
        library.printAvailableBook();

        System.out.println("\n--- После возврата: ---");
        library.returnBook("1984");
        library.printAvailableBook();
    }
}
