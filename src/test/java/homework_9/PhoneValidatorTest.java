package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {
    /**
     * Позитивные кейсы:
     * Корректные номера:
     * "+1 1234567890" → true.
     * "+44 9876543210" → true.
     * "+999 1111111111" → true.
     * ----------------------------
     * Негативные кейсы
     * Некорректные номера:
     * "12345" → false.
     * "invalid" → false.
     * "+1 abcdefghij" → false.
     * "+1234 1234567890" (слишком длинный код страны) → false.
     * "+1 123" (недостаточно цифр) → false.
     * null → Должно выбрасываться IllegalArgumentException.
     * ------------------------------
     * Угловые кейсы:
     * "" (пустая строка) → false.
     */

    private final PhoneValidator phoneValidator = new PhoneValidator();

    @DisplayName("Проверка корректных номеров")
    @ParameterizedTest
    @ValueSource(strings = {"+1 1234567890", "+44 9876543210", "+999 1111111111"})
    public void userCanValidateCorrectPhone(String phone) {

        boolean actualResult = phoneValidator.isValidPhoneNumber(phone);

        assertTrue(actualResult, "Ожидаем корректный формат -> true");
    }

    @DisplayName("Проверка некорректных номеров")
    @ParameterizedTest
    @ValueSource(strings = {"12345", "invalid", "+1 abcdefghij", "+1234 1234567890", "+1 123", ""})
    public void userCanValidateInCorrectPhone(String phone) {

        boolean actualResult = phoneValidator.isValidPhoneNumber(phone);

        assertFalse(actualResult, "Ожидаем некорректный форма -> false");
    }

    @DisplayName("Исключение при передаче значения null")
    @Test
    public void should() {
        // NullPointerException такое исключение должно быть, в подсказках IllegalArgumentException, оставил как в них написано
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.isValidPhoneNumber(null);
        }, "По подсказкам должно быть выброшено исключение IllegalArgumentException");
    }
}