package homework_10;

public class DebugTask7 {
    private static volatile int balance = 100;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> withdraw(60));
        Thread t2 = new Thread(() -> withdraw(50));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    public static synchronized void withdraw(int amount) {
        if (balance >= amount) {
            try { Thread.sleep(100); } catch (InterruptedException e) { }
            balance -= amount;
            System.out.println("New balance: " + balance);
        }
    }
}
