package practice_5.contact_book;

public class Main {
    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();

        contactBook.addContact("Nikita", 894123);
        contactBook.addContact("Kolya", 444992827);

        contactBook.printContacts();
        contactBook.updatePhone("Kolya", 911);
        contactBook.printContacts();
    }
}
