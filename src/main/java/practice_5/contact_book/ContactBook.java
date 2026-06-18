package practice_5.contact_book;

import java.util.HashMap;

public class ContactBook {
private HashMap<String, Integer> contacts;

public ContactBook(){
    this.contacts = new HashMap<>();
}

// добавить контакт
    public void addContact(String name, Integer phone) {
    contacts.put(name, phone);
    }
    // поиск контактов по имени
        public Integer getPhone(String name) {
    return contacts.get(name);
        }

    // обновление телефона по имени
    public void updatePhone(String name, Integer updatePhone) {
    contacts.put(name, updatePhone);
    }

    public void printContacts() {
        System.out.println("Все контакты");
    contacts.forEach(
            (name, phone) -> {
                System.out.println("Name: " + name + " Phone: " + phone);
            }
    );
        System.out.println();
    }
}
