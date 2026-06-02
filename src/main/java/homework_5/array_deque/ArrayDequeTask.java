package homework_5.array_deque;

import java.util.ArrayDeque;

public class ArrayDequeTask {

    private ArrayDeque<Integer> arrayDeque;

    ArrayDequeTask() {
        this.arrayDeque = new ArrayDeque<>();
    }

    public void addElements(int integer) {
        arrayDeque.add(integer);
    }

    public void printElement() {
        arrayDeque.forEach(System.out::println);
    }
}
