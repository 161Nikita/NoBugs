package homework_6.exception.validate_email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateEmail {

    public void validateEmail(String email) {
        String regex = ".*@.*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new InvalidEmailException("Отсутствует @");
        }
        System.out.println("Адрес успешно провалидирован");
    }
}
