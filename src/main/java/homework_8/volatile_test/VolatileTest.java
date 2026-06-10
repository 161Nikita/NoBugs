package homework_8.volatile_test;

public class VolatileTest {
private static volatile boolean stop = false;
private static long counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!stop) {
                counter++;
                System.out.println("Счетчик побежал: " + counter);
            }
        });
        t1.start();
        Thread.sleep(2000);
        stop = true;
        t1.join();
        System.out.println("Поток остановился");
    }
}
