package homework_5.array_deque;

public class Main {
    public static void main(String[] args) {

        ArrayDequeTask arrayDequeTask = new ArrayDequeTask();

        arrayDequeTask.addElements(2);
        arrayDequeTask.addElements(3);
        arrayDequeTask.addElements(10);
        arrayDequeTask.addElements(0);
        arrayDequeTask.addElements(9);

        arrayDequeTask.printElement();
    }
}
