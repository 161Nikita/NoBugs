package homework_6.exception.age_validator;

public class AgeValidator {
    public void checkAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
           throw new InvalidAgeException("Возраст указан неверно!");
        }
        System.out.println(age);
    }
}
