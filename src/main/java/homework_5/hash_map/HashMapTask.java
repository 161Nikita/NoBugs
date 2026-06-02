package homework_5.hash_map;

import java.util.HashMap;

public class HashMapTask {

    HashMap<String, Integer> hashMap;

    public HashMapTask() {
        this.hashMap = new HashMap<>();
    }

    public void addRecord(String string, int integer) {
        hashMap.put(string, integer);
    }

    public void printAllRecords() {
        hashMap.forEach((string, integer) -> System.out.println("Имя: " + string + " Возраст: " + integer));
    }

    public void printUserUnder18() {
        hashMap.forEach((name, age) -> {
            if (age < 18) {
                System.out.println("Имя: " + name + " Возраст: " + age);
            }
        });
    }

}
