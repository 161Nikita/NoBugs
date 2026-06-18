package homework_8.synchronized_test;

public class Counter {

    public int count = 0;

    public synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName() + ":" + count);
    }
}
