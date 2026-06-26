package homework_11_tests.user_validate_tests;

import homework_11.user_validator.InvalidUserException;
import homework_11.user_validator.User;
import homework_11.user_validator.ValidatorUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidatorTest {
    /**
     * Позитивные кейсы:
     *
     * Имя с заглавной латинской буквы N -> N
     * Имя с заглавной буквы на кириллице Н -> Н
     *
     * Возраст 19 -> 19
     * Возраст 99 -> 99
     *
     * Е-мэйл соответствует регулярке -> nikita.12@yandex.ru
     * Е-мэйл соответствует регулярке -> Nnikita.12@google.com
     *
     * Угловые кейсы:
     *
     * Возраст 18
     * Возраст 100
     *
     * Негативные кейсы:
     *
     * Имя со строчной буквы n -> InvalidUserException
     * Возраст 17 -> InvalidUserException
     * Возраст 101 -> InvalidUserException
     * Е-мэйл Nikita.12google.com -> InvalidUserException
     * Е-мэйл Nikita.12@googlecom -> InvalidUserException
     */

    ValidatorUser validator = new ValidatorUser();

    @BeforeEach
    public void validationEnabled(){
        ValidatorUser.validationEnabled = true;
    }

@Test
@DisplayName("Валидные входные данные. Имя -> N, возраст -> 19, емэйл -> nikita.12@yandex.ru")
public void validateUserTest(){
    User user = new User("Nikita", 19, "nikita.12@yandex.ru");

    assertDoesNotThrow(() -> validator.validate(user));
}

    @Test
    @DisplayName("Возраст 99->99")
    public void validateAgeUserTest() {
        User user = new User("Никита", 99, "Nnikita.12@google.com");

        assertDoesNotThrow(() -> validator.validate(user));
    }

    @Test
    @DisplayName("Возраст 18->18 и 100->100")
    public void validateAgeUserCornerCaseTest() {
        User user1 = new User("Ivan", 18, "Nnikita12@google.com");
        User user2 = new User("Иван", 100, "Nnikita-12@google.com");

        assertDoesNotThrow(() -> validator.validate(user1));
        assertDoesNotThrow(() -> validator.validate(user2));
    }

    @Test
    @DisplayName("Имя со строчной буквы n -> InvalidUserException")
    public void invalidateNameWithLowCaseTest() {
        User user = new User("nikita", 34, "nikita@gmail.com");

        assertThrows(InvalidUserException.class, () -> validator.validate(user));
    }

    @Test
    @DisplayName("Возраст 17 -> InvalidUserException и возраст 101 -> InvalidUserException")
    public void invalidateAgeTest() {
        User user1 = new User("Nikita", 17, "nikita@gmail.com");
        User user2 = new User("Nikita", 101, "nikita@gmail.com");

        assertThrows(InvalidUserException.class, () -> validator.validate(user1));
        assertThrows(InvalidUserException.class, () -> validator.validate(user2));
    }

    @Test
    @DisplayName("Email Nikita.12google.com -> InvalidUserException и email Nikita.12@googlecom -> InvalidUserException")
    public void invalidateEmailTest() {
        User user1 = new User("Nikita", 35, "Nikita.12google.com");
        User user2 = new User("Petr", 55, "nikita.12@googlecom");

        assertThrows(InvalidUserException.class, () -> validator.validate(user1));
        assertThrows(InvalidUserException.class, () -> validator.validate(user2));
    }
}
