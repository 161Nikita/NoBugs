package homework_5.linked_hash_map;

import java.util.LinkedHashMap;

public class LinkedHashMapTask {

    LinkedHashMap<String, Integer> linkedHashMapTask;

    LinkedHashMapTask() {
        this.linkedHashMapTask = new LinkedHashMap<>();
    }

    public void addContact(String name, int phoneNumber) {
        linkedHashMapTask.put(name, phoneNumber);
    }

    public void searchContact(String name) {
        System.out.println("Номер телефона контакта " + name +": "+ linkedHashMapTask.get(name));
    }

    public void printContact() {
        linkedHashMapTask.forEach((name, phoneNumber) -> System.out.println("Имя: " + name + " номер: " + phoneNumber));
    }
}
