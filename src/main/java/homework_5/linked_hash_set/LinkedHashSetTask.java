package homework_5.linked_hash_set;

import java.util.LinkedHashSet;

public class LinkedHashSetTask {

    private LinkedHashSet<Integer> linkedHashSet;

    public LinkedHashSetTask() {
        this.linkedHashSet = new LinkedHashSet<>();
    }

    public void addElementsWithoutDublicates(int ineger) {
        if (!linkedHashSet.contains(ineger)) {
            linkedHashSet.add(ineger);
        } else {
            System.out.println("Число: " + ineger + " является дубликатом!");
        }
    }

    public void  printElements() {
        linkedHashSet.forEach(System.out::println);
    }
}
