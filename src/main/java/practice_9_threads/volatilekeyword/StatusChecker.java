package practice_9_threads.volatilekeyword;

public class StatusChecker implements Runnable{
    // если active = false, все потоки должны завершить исполнение
    private volatile boolean active = true;

    @Override
    public void run() {
        while (active){
            System.out.println(Thread.currentThread().getName() + " он бежит");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.active = false;
    }
}
