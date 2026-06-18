package homework_5.priority_queue;

public class Main {
    public static void main(String[] args) {

        PriorityQueueTask priorityQueueTask = new PriorityQueueTask();

        priorityQueueTask.addElementInQueue(2);
        priorityQueueTask.addElementInQueue(4);
        priorityQueueTask.addElementInQueue(50);
        priorityQueueTask.addElementInQueue(40);
        priorityQueueTask.addElementInQueue(20);

        priorityQueueTask.outDeleteQueue();
    }


}
