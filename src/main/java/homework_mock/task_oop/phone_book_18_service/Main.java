package homework_mock.task_oop.phone_book_18_service;

/*
"В системе есть телефонная книга.
Телефонная книга хранит контакты. PhoneBook
У каждого контакта есть:
имя
номер телефона
Нужно реализовать систему, которая может:
добавить контакт
удалить контакт
найти номер по имени
проверить, существует ли контакт
показать количество контактов

Правила:
имя контакта уникально
если контакт с таким именем уже существует, номер должен обновляться
если контакт удален, его нельзя найти

Пример:
Добавляем контакты:
Alex → 12345
Maria → 77777

Поиск:
Alex → 12345

Удаляем:
Maria

Количество контактов:
1"
 */


public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        System.out.println("Добавляем контакты:");
        phoneBook.addContact("Alex", "12345");
        System.out.println("Alex → " + phoneBook.searchNumber("Alex"));
        phoneBook.addContact("Maria", "77777");
        System.out.println("Maria → " + phoneBook.searchNumber("Maria"));
        System.out.println("\nПоиск:");
        System.out.println("Alex → " + phoneBook.searchNumber("Alex"));
        System.out.println("\nУдаляем:");
        phoneBook.removeContact("Maria");
        System.out.println("Maria");
        System.out.println("\nКоличество контактов:");
        System.out.println(phoneBook.getAllContacts());
    }
}
