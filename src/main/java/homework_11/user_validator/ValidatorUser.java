package homework_11.user_validator;

import java.util.regex.Pattern;

public class ValidatorUser {

    public static boolean validationEnabled = true;

    public void validate(User user) {
        if (!validationEnabled)
            return;
        validateName(user.getName());
        validateAge(user.getAge());
        validateEmail(user.getEmail());
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidUserException("Имя не должно быть null или пустым");
        }
        if (!Character.isUpperCase(name.charAt(0))) {
            throw new InvalidUserException("Имя должно начинаться с заглавной буквы");
        }
    }

    private void validateAge(int age) {
        if (!(age >= 18 && age <= 100)) {
            throw new InvalidUserException("Возраст должен быть от 18 до 100 включительно");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidUserException("Email должен соответствовать стандартному формату электронной почты");
        }
    }

}