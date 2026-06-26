package homework_mock;

public class SafeDivision {
    public static int safeDivide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.print("[Лог: обнаружено деление на 0 -> " + e.getMessage() + "] ");
            return 0; // Возврат дефолтного значения
        }
    }

    public static void main(String[] args) {
        System.out.println("Результат 10 / 2 (5): " + safeDivide(10, 2));
        System.out.println("Результат 10 / 0 (0): " + safeDivide(10, 0));
    }
}
