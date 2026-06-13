package practice_10_tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("class StringProcessor, method reverse")
public class ReverseStringTest extends StringProcessorTest {
    /**
     * Тесты для переворота строки:
     * Happy path: "nikita" -> "atikin"
     * corner case:
     * "" -> ""
     * "a" -> "a"
     * null -> IllegalArgument Exception
     */

    public static Stream<Arguments> validStringsToRevers() {
        return Stream.of(
                // happy path: "nikita" -> "atikin"
                Arguments.of("nikita", "atikin"),
                // corner cases: "" -> ""
                Arguments.of("", ""),
                // corner cases; "a" -> "a"
                Arguments.of("a", "a"));
    }

    @ParameterizedTest
    @MethodSource("validStringsToRevers")
    public void userCanReverseValidString(String initialString, String expectedString) {

        String reversedString = stringProcessor.reverse(initialString);

        assertEquals(expectedString, reversedString, "String reversed incorrectly!!!");
    }

    @Test
    public void userCannotReverseNullString() {

        assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.reverse(null);
        }, "Reversing of null string should lead to IllegalArgumentException");
    }
}