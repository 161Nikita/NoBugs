package common.utils;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Принимаем на вход общего ретрая:
 * 1) что повторяем
 * 2) условие выхода
 * 3) максимальное количество попыток
 * 4) Задержка между каждой попыткой
 */
public class RetryUtils {
    public static <T> T retry(Supplier<T> action, Predicate<T> condition, int maxAttempts, long delayMillis) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Количество попыток должно быть больше 0");
        }

        T result = null;
        int attempts = 0;

        while (attempts < maxAttempts) {
            attempts++;

            result = action.get();

            if (condition.test(result)) {
                return result;
            }

            if (attempts < maxAttempts && delayMillis > 0) {
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Прервано ожидания повтора", e);
                }
            }
        }
        throw new RuntimeException("Retry failed after " + maxAttempts + " attempts. Последний результат: " + result);
    }
}