package homework_mock.algos.task_algos;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Вывод элементов очереди. Использовать queue для вывода элементов в порядке добавления.
 *
 */

public class PrintQueue {

    public static void printQueue(Queue<String> queue) {

        if (queue == null) {
            throw new IllegalArgumentException("Очередь не может быть null");
        }
        if (queue.isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        Queue<String> tempQueue = new ArrayDeque<>(queue);

        while (!tempQueue.isEmpty()) {
            System.out.println(tempQueue.poll());
        }
    }

    public static void main(String[] args) {

        Queue<String> queue1 = new LinkedList<>();
        queue1.offer("Первый");
        queue1.offer("Второй");
        queue1.offer("Третий");

        printQueue(queue1);
        System.out.println(queue1.size());

        try {
            printQueue(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}