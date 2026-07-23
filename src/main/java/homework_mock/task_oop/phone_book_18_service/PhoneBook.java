package homework_mock.task_oop.phone_book_18_service;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private final Map<String, String> contactBook = new HashMap<>();

    // добавить контакт
    public void addContact(String name, String phone) {
        contactBook.put(name, phone);
    }

    // удалить контакт
    public void removeContact(String name) {
        contactBook.remove(name);
    }

    // найти номер по имени
    public String searchNumber(String name) {
        return contactBook.get(name);
    }

    // проверить, существует ли контакт
    public void checkContact(String name) {
        if (contactBook.containsKey(name)) {
            System.out.println("Да, контакт " + name + " существует");
        } else System.out.println("Нет, контакт " + name + " не существует");
    }

    // показать количество контактов
    public int getAllContacts() {
        return contactBook.size();
    }
}