package homework_12.dry;

/*
Нарушение DRY (Don't Repeat Yourself) – дублирование кода
Задача: Устраните дублирование кода, применив перегрузку методов или использование массива аргументов.
 */

public class MathOperations {

    public static int add(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(1, 2)); // 3
        System.out.println(add(1, 2, 3)); // 6
        System.out.println(add(1, 2, 3, 4)); // 10
        System.out.println(add(1, 2, 3, 4, 10)); // 20
    }

}
