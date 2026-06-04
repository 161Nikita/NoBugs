package practice_7.exception.unchecked;

public class IndexOutOfBoundsExceptionExample {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            int number = numbers[5]; // Попытка доступа к несуществующему индексу
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Попытка доступа к индексу за пределами массива.");
        }
    }
}
