package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayUtilsTest {
    /**
     * Позитивные кейсы:
     * Обычный массив [3, 5, 7, 2] -> [7]
     * Отрицательные числа в массиве [-3, -5, -7, -2] -> [-2]
     * Угловые тесты:
     * Один элемент в массиве [2] -> [2]
     * Негативные кейсы:
     * Пустой массив [null] -> NoSuchElementException
     */

    private final ArrayUtils arrayUtils = new ArrayUtils();

    public static Stream<Arguments> enterValidArrays() {
        return Stream.of(
                // Позитивные кейсы:
                Arguments.of(new int[]{3, 5, 7, 2}, 7),
                Arguments.of(new int[]{-3, -5, -7, -2}, -2),
                Arguments.of(new int[]{2}, 2));
    }

    @DisplayName("Поиск максимального числа в валидном массиве")
    @ParameterizedTest
    @MethodSource("enterValidArrays")
    public void userCanFindMaxInArrays(int[] numbers, int expected) {

        int actualResult = arrayUtils.findMax(numbers);

        assertEquals(expected, actualResult, "Поиск максимального значения в массиве");
    }

    @DisplayName("Обработка ошибки при передачи пустого массива")
    @Test
    public void shouldNoSuchElementException() {

        assertThrows(NoSuchElementException.class, () -> {
            arrayUtils.findMax(new int[]{});
        }, "Выбрасывается исключение, если передали пустой массив");
    }
}