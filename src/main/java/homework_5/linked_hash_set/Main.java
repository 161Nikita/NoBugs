package homework_5.linked_hash_set;

public class Main {
    public static void main(String[] args) {

        LinkedHashSetTask linkedHashSetTask = new LinkedHashSetTask();

        linkedHashSetTask.addElementsWithoutDublicates(2);
        linkedHashSetTask.addElementsWithoutDublicates(3);
        linkedHashSetTask.addElementsWithoutDublicates(123);

        linkedHashSetTask.printElements();

        linkedHashSetTask.addElementsWithoutDublicates(123);

        linkedHashSetTask.printElements();
    }
}