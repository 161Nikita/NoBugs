package homework_5.hash_set;

import java.util.*;

public class HashSetTask {

    private List<String> list;

    public HashSetTask() {
        this.list = new LinkedList<>();
    }

    public List<String> getList() {
        return this.list;
    }

    public void addList(String string) {
        list.add(string);
    }

    public Set<String> noDublicates(List<String> list) {
        return new HashSet<>(list);
    }

    public void printList() {
        System.out.println();

        list.forEach(System.out::println);

        System.out.println();
    }
}
