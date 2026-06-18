package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearTest {
    /**
     * Позитивные кейсы:
     * Високосные (2020, 2000, 1600) -> true
     * Негативные кейсы:
     * Обычные годы (2026)-> false
     * Года, которые делятся на 100, но не на 400 (1900, 2100) -> false
     */

    private final LeapYear leapYear = new LeapYear();

    @DisplayName("Проверка: год високосный")
    @ParameterizedTest
    //Позитивные кейсы: Високосные (2020, 2000, 1600) -> true
    @ValueSource(ints = {2020, 2000, 1600})
    public void userEntersALeapYear(int year) {

        boolean actualResult = leapYear.isLeapYear(year);

        assertTrue(actualResult, "Проверяемый год является високосным -> true");
    }

    @DisplayName("Проверка: год не является високосным")
    @ParameterizedTest
    //Негативные кейсы: Обычные годы (2026)-> false
    //Года, которые делятся на 100, но не на 400 (1900, 2100) -> false
    @ValueSource(ints = {2026, 1900, 2100})
    public void userEntersANotLeapYear(int year) {

        boolean actualResult = leapYear.isLeapYear(year);

        assertFalse(actualResult, "Проверяемый год не является високосным -> false");
    }
}