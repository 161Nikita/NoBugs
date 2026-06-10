package practice_9_threads.volatilekeyword;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        long start = System.nanoTime();

        StatusChecker statusChecker = new StatusChecker();
        Thread t1 = new Thread(statusChecker);
        t1.start();
        Thread.sleep(500);
        statusChecker.stop();
        Thread t2 = new Thread(statusChecker::stop);
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println("Время остановки статус чеккера без volatile: " + (end - start));
    }
}
