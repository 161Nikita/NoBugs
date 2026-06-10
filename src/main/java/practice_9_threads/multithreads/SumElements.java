package practice_9_threads.multithreads;

public class SumElements {
    // посчитать сумму элементов большого массива с помощью разделения на части
    // и использовании нескольких потоков
    // разделим массив на 2 части пополам
    // первый массив: 0 - array.length/2
    // второй массив: array.length/2 + 1 - array.length

    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static void main(String[] args) throws InterruptedException {
        int sum = parallelSum(array);
        System.out.println("Сумма: " + sum);
    }

    public static int parallelSum(int[] array) throws InterruptedException {
        int halfSize = array.length / 2;

        ThreadSum firstHalf = new ThreadSum(array, 0, halfSize);
        ThreadSum secondHalf = new ThreadSum(array, halfSize, array.length);

        Thread t1 = new Thread(firstHalf);
        Thread t2 = new Thread(secondHalf);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return firstHalf.getSum() + secondHalf.getSum();
    }
}
