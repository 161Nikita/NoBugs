package homework_5.hash_set;


import java.util.HashSet;

public class HashSetTaskTwo {

    private HashSet<String> hashSet;

    public HashSetTaskTwo() {
        this.hashSet = new HashSet<>();
    }

    public void addNames(String name) {
        hashSet.add(name);
    }

    public void printNames() {
        hashSet.forEach(System.out::println);
    }

    public void checkDublicatesNames(String string) {
        if(hashSet.contains(string)) {
            System.out.println("Имя: " + string + " в множестве уже есть, добавить не получится");
        } else {
            System.out.println("Имя: " + string + " в множестве нет, можем его туда добавить");
        }
    }
}
