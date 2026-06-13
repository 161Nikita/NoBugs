package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {
    /**
     * Позитивные кейсы:
     * Корректный адрес "test@example.com" -> true
     * Негативные кейсы:
     * Некорректные email ("bad@.com", "no-at-symbol")
     * Угловые кейсы:
     * Ввели null -> null
     */

    public final EmailValidator emailValidator = new EmailValidator();

    @DisplayName("Проверка: корректный e-mail")
    @ParameterizedTest
    //Позитивные кейсы: Корректный адрес "test@example.com" -> true
    @ValueSource(strings = {"test@example.com"})
    public void userEnterValidEmail(String email) {

        boolean actualResult = emailValidator.isValidEmail(email);

        assertTrue(actualResult, "Проверяемый email должен быть валидным -> true");
    }

    @DisplayName("Проверка: некорректный e-mail")
    @ParameterizedTest
    //Негативные кейсы:Некорректные email ("bad@.com", "no-at-symbol")
    @ValueSource(strings = {"bad@.com", "no-at-symbol"})
    public void userEnterNotValidEmail(String email) {

        boolean actualResult = emailValidator.isValidEmail(email);

        assertFalse(actualResult, "Проверяемый email не должен быть валидным -> false");
    }

    @DisplayName("Обработка значения null, при вводе null")
    @Test
    //Угловые кейсы: Пользователь ввел null -> false
    public void userEnterNull() {

        boolean actualResult = emailValidator.isValidEmail(null);

        assertFalse(actualResult, "если ввели null -> возвращаем false ");
    }
}