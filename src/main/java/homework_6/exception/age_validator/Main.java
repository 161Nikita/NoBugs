package homework_6.exception.age_validator;

public class Main {
    public static void main(String[] args) {

        AgeValidator ageValidator = new AgeValidator();
        try {
            ageValidator.checkAge(151);
        } catch (InvalidAgeException e) {
            System.out.println("Произошла ошибка " + e.getMessage());
        }
    }
}
