package homework_5.priority_queue;

import java.util.PriorityQueue;

public class PriorityQueueTask {

   private PriorityQueue<Integer> priorityQueue;

   public PriorityQueueTask() {
       this.priorityQueue = new PriorityQueue<>();
   }

   public void addElementInQueue(int integer) {
       priorityQueue.add(integer);
   }

   public void outDeleteQueue() {
       while (!priorityQueue.isEmpty()) {
           System.out.println(priorityQueue.poll());
       }
   }
}
