package practice_9_threads.multithreads;

public class ThreadSum implements Runnable {
    private final int[] array;
    private final int start;
    private final int end;
    private int sum = 0;

    public ThreadSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++)
            sum = sum + array[i];
    }

    public int getSum() {
        return this.sum;
    }
}
