package homework_5.linked_list;

import java.util.LinkedList;

public class LinkedListTask {

    private LinkedList<String> linkedList;

    public LinkedListTask() {
        this.linkedList = new LinkedList<>();
    }

    public void addString(String string) {
        linkedList.add(string);
    }

    public void addNextTask(String string) {
        linkedList.poll();
    }

    public void addFirstTask(String string) {
       linkedList.addFirst(string);
    }

    public void printString() {
        System.out.println("Все задачи: ");
        linkedList.forEach(System.out::println);
        System.out.println();

    }
}