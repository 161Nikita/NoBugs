package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {
    /**
     * Позитивные кейсы:
     * Маленькие числа (1! -> 1, 5! -> 120, 7! -> 5040)
     * Угловые кейсы:
     * 0! = 1 -> 1
     * Негативные кейсы:
     * Отрицательные числа (должно выбрасываться исключение) -> IllegalArgumentException
     */

    private final Factorial factorial = new Factorial();

    public static Stream<Arguments> isEnterValidFactorial() {
        return Stream.of(
                // Позитивные кейсы
                Arguments.of(1, 1),
                Arguments.of(5, 120),
                Arguments.of(7, 5040),
                Arguments.of(0, 1));
    }

    @DisplayName("Вычисления факториала для валидных чисел")
    @ParameterizedTest
    @MethodSource("isEnterValidFactorial")
    public void userCanCalculateFactorial(int number, int expectedNumber) {

        int actualResult = factorial.factorial(number);

        assertEquals(expectedNumber, actualResult, "Ожидаем корректное вычисление факториала");
    }

    @DisplayName("Обработка ошибки при вводе отрицательного числа")
    @Test
    public void shouldExceptionIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            factorial.factorial(-1);
        }, "Выбрасываем исключение так как передали отрицательное число");
    }
}