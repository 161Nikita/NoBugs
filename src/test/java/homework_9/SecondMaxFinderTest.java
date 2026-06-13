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

public class SecondMaxFinderTest {
    /**
     * Позитивные кейсы:
     * Обычные массивы
     * Негативные кейсы:
     * Массив с одинаковыми числами (должно выбрасываться исключение) {4.4.4.4} -> NoSuchElementException
     * Один элемент в массиве (должно выбрасываться исключение) {4} -> NoSuchElementException (выбрасывается IllegalArgumentException)
     * Пустой массив (должно выбрасываться исключение) {} -> NoSuchElementException (выбрасывается IllegalArgumentException)
     */

    private final SecondMaxFinder secondMaxFinder = new SecondMaxFinder();

    public static Stream<Arguments> enterValidArrays() {
        return Stream.of(Arguments.of(new int[]{3, 5, 7, 2}, 5));
    }

    @DisplayName("Проверка: Поиск второго максимального числа в массиве")
    @ParameterizedTest
    @MethodSource("enterValidArrays")
    public void userCanFindSecondMaxNumber(int[] numbers, int expected) {

        int actualResult = secondMaxFinder.findSecondMax(numbers);

        assertEquals(expected, actualResult, "Ожидаем корректное второе по величине число в массиве");
    }

    @DisplayName("Исключение при передаче одинаковых чисел в массиве")
    @Test
    public void shouldThrowNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            secondMaxFinder.findSecondMax(new int[]{4, 4, 4, 4});
        }, "Выбрасываем исключение при всех одинаковых значениях в массиве");
    }

    // NoSuchElementException.class не подходит, т.к. в методе не предусмотрели пустой массив и массив с одним элементом
    // поэтому, я предполагаю, выбрасывается другое исключение, IllegalArgumentException потому что оно значит, что ввели
    // некорректное значение
    @DisplayName("Исключение при передаче одного элемента в массиве")
    @Test
    public void shouldThrowIllegalArgumentExceptionOneElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            secondMaxFinder.findSecondMax(new int[]{4});
        }, "Выбрасываем исключение при передаче одного элемента в массиве");
    }

    // NoSuchElementException.class не подходит, т.к. в методе не предусмотрели пустой массив и массив с одним элементом
    // поэтому, я предполагаю, выбрасывается другое исключение, IllegalArgumentException потому что оно значит, что ввели
    // некорректное значение
    @DisplayName("Исключение при передаче пустого элемента в массиве")
    @Test
    public void shouldThrowIllegalArgumentExceptionZeroElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            secondMaxFinder.findSecondMax(new int[]{});
        }, "Выбрасываем исключение при передаче пустого элемента в массиве");
    }
}