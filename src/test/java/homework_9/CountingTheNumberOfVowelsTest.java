package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountingTheNumberOfVowelsTest {

    /**
     * Позитивные проверки:
     * hello -> 2
     * java -> 2
     * AEIOU -> 5 // только гласные и верхний регистр
     * Corner cases:
     * "" -> 0
     * "qwrt" -> 0
     * Негативные кейсы:
     * null -> IllegalArgumentException
     */

    private final CountingTheNumberOfVowels countingTheNumberOfVowels = new CountingTheNumberOfVowels();


    public static Stream<Arguments> enterValidString() {
        return Stream.of(
                // позитивные проверки: hello -> 2
                Arguments.of("hello", 2),
                // позитивные проверки: java -> 2
                Arguments.of("java", 2),
                // позитивные проверки: AEIOU -> 5
                Arguments.of("AEIOU", 5),
                // Corner cases: "" -> 0
                Arguments.of("", 0),
                // Corner cases: "qwrt" -> 0
                Arguments.of("qwrt", 0));
    }


    @ParameterizedTest
    @DisplayName("Подсчет гласных букв в слове")
    @MethodSource("enterValidString")
    public void userCanCountVowels(String initialString, int expectedNumber) {

        int countNumber = countingTheNumberOfVowels.countVowels(initialString);

        assertEquals(expectedNumber, countNumber, "Ожидаем количество гласных букв в слове, передаваемом в методе");
    }

    @Test
    @DisplayName("Обработка ошибки при вводе null")
    public void shouldExceptionWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            countingTheNumberOfVowels.countVowels(null);
        }, "Выбрасывается исключение если передали null");
    }

}
