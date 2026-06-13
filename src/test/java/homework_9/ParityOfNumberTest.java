package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParityOfNumberTest {

    /**
     * Позитивные проверки:
     * число четное 2 -> true
     * число четное 0 -> true
     * число нечетное 1 -> false
     * Угловые проверки:
     * число четное -2 -> true
     * число нечетное -1 -> false
     */

    private final ParityOfNumber parityOfNumber = new ParityOfNumber();

    public static Stream<Arguments> numberIsEven() {
        return Stream.of(
                // позитивный кейс: число четное 2 -> true
                Arguments.of(2),
                // позитивный кейс: число четное 0 -> true
                Arguments.of(0),
                // Угловые проверки: число четное -2 -> true
                Arguments.of(-2));
    }

    public static Stream<Arguments> numberIsOdd() {
        return Stream.of(
                // позитивный кейс: число нечетное 1 -> false
                Arguments.of(1),
                // Угловые проверки: число нечетное -1 -> false
                Arguments.of(-1));
    }

    @ParameterizedTest
    @MethodSource("numberIsEven")
    @DisplayName("Проверка, что число является четным")
    public void userCanCheckIfIsNumberIsEven(int number) {

        boolean actualResult = parityOfNumber.isEven(number);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @MethodSource("numberIsOdd")
    @DisplayName("Проверка, что число является нечетным")
    public void userCanCheckIfIsNumberIsOdd(int number) {

        boolean actualResult = parityOfNumber.isEven(number);

        assertFalse(actualResult);
    }
}