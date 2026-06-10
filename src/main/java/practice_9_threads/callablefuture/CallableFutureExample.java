package practice_9_threads.callablefuture;

import java.util.Random;
import java.util.concurrent.*;

public class CallableFutureExample {
    // Сложные расчеты ДНК
    // Наша задача дождаться вычисления и получить результат

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> dnaResult = () -> {
            System.out.println("Сложное вычисление ДНК");
            Thread.sleep(10000);
            int randomDnaResult = new Random().nextInt();
            return randomDnaResult;
        };

        Future<Integer> future = executorService.submit(dnaResult);

        System.out.println("Результат ДНК: " + future.get());

        executorService.shutdown();
    }
}
