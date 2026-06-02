package homework_5.linked_hash_map;

public class Main {
    public static void main(String[] args) {

        LinkedHashMapTask linkedHashMapTask = new LinkedHashMapTask();

        linkedHashMapTask.addContact("Nikita", 991100);
        linkedHashMapTask.addContact("Kseniya", 48282);
        linkedHashMapTask.addContact("Yana", 338465);

        linkedHashMapTask.printContact();

        linkedHashMapTask.searchContact("Nikita");
    }
}
