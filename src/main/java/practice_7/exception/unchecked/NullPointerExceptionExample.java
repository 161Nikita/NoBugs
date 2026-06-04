package practice_7.exception.unchecked;

public class NullPointerExceptionExample {
    public static void main(String[] args) {
        try {
            String text = null;
            int lenght = text.length(); // Это вызовут NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Попытка вызова метода у null объекта.");
        }
    }
}
