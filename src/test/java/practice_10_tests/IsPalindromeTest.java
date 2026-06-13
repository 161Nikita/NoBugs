package practice_10_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class IsPalindromeTest extends StringProcessorTest{
    /**
     * Тесты для проверки, является ли палиндромом:
     * позитивные кейсы:
     * - четное количество: "abba" -> true
     * - нечетное количество: "hah" -> true
     * негативные кейсы:
     * - "John" -> false
     * - corner cases:
     * - "a" -> true
     * - "" -> true
     * - null -> IlligalArgumentExcrption
     */

    @ParameterizedTest
    @ValueSource(strings = {
            //позитивные кейсы
            "abba", "hah",
            // corner cases
            "a", ""})
    public void userCanCheckIfValidStringIsPalindrome(String initiolString) {

        boolean actualResult = stringProcessor.isPalindrome(initiolString);

        assertTrue(actualResult);
    }

    @Test
    public void userCanCheckIfValidStringIsNotPalindrome() {

        String initialString = "John";

        boolean actualResult = stringProcessor.isPalindrome(initialString);

        assertFalse(actualResult);
    }
    @Test
    public void userCannotCheckIfNullStringPalindrome() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.reverse(null);
        }, "Checking if Null string is palindrome should lead to IllegalArgumentException");
    }
}
