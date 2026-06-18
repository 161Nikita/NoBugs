package homework_6.exception.validate_email;

public class Main {
    public static void main(String[] args) {
        ValidateEmail validateEmail = new ValidateEmail();

        try {
            validateEmail.validateEmail("asd@ad.ru");
        } catch (InvalidEmailException e) {
            System.out.println("Ошибка" + e.getMessage());
        }

        try {
            validateEmail.validateEmail("sadas.ru");
        } catch (InvalidEmailException e) {
            System.out.println("Отловили ошибку " + e.getMessage());
        }
    }
}
