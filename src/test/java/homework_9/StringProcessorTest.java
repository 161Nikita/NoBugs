package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringProcessorTest {
    /**
     * Позитивные кейсы:
     * "java" -> "avaj"
     * Угловые кейсы:
     * "" -> ""
     * "a" -> "a"
     * "Привет, как дела?"
     * "HeLlo"
     * Негативные кейсы:
     * null -> null
     */

    private final StringProcessor stringProcessor = new StringProcessor();

    public static Stream<Arguments> inputString() {
        return Stream.of
                // Позитивные кейсы
                        (Arguments.of("java", "avaj"),
                                // Угловые кейсы:
                                (Arguments.of("", "")),
                                (Arguments.of("a", "a")),
                                (Arguments.of("Привет, как дела?", "?алед как ,тевирП")),
                                (Arguments.of("HeLlo", "olLeH")));

    }

    @DisplayName("Разворот валидных строк")
    @ParameterizedTest
    @MethodSource("inputString")
    public void userCanReverseString(String initial, String expected) {

        String actualString = stringProcessor.reverse(initial);

        assertEquals(expected, actualString, "Ожидаем перевернутую строку");
    }

    @DisplayName("Обработка значения null, при вводе null")
    @Test
    public void userCanInputNull() {

        String actualString = stringProcessor.reverse(null);

        assertNull(actualString, "Ожидаем, что метод возвращает null, если ввели null");
    }
}