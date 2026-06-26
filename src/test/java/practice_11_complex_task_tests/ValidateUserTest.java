package practice_11_complex_task_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice_11_complex_tasks.validate_user.InvalidUserException;
import practice_11_complex_tasks.validate_user.User;
import practice_11_complex_tasks.validate_user.UserValidate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateUserTest {

    private final UserValidate validator = new UserValidate();

    @BeforeEach
    public void enableValidation() {
        UserValidate.validationEnabled = true;
    }

    @Test
    void testValidUser() {
        User user = new User("Ivan", 30, "Ivan@example.com");
        assertDoesNotThrow(() -> validator.validate(user));
    }

    @Test
    public void validateNameTest() {
        User user = new User(null, 30, "nikita@gmail.com");

        assertThrows(InvalidUserException.class, () -> validator.validate(user));
    }

    @Test
    public void validateAgeTest() {
        User user = new User("Nikita", 101, "nikita@gmail.com");

        assertThrows(InvalidUserException.class, () -> validator.validate(user));
    }

    @Test
    public void validateEmailTest() {
        User user = new User("Nikita", 50, "nikitagmail.com");

        assertThrows(InvalidUserException.class, () -> validator.validate(user));
    }
}
