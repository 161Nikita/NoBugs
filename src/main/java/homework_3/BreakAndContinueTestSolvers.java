package homework_3;

import java.util.Scanner;

public class BreakAndContinueTestSolvers {
    public static void main(String[] args) {
        // проверка метода на сумму чисел до первого отрицательного
        //sumNumbersFirstNegative();
        // проверка метода на пропуск чисел, делящихся на 3
        //skippingNumbersDivisible();
        // проверка метода на вывод только положительных чисел
        // positiveNumber();
        // проверка метода на ввод строк до команды "stop"
        inputString();


    }

    public static void sumNumbersFirstNegative() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;

        System.out.println("Введите числа чтобы их сложить");

        while (true) {
            int console = scanner.nextInt();
            if (console < 0) {
                break;
            }
            sum = sum + console;
        }
        System.out.println(sum);
    }

    public static void skippingNumbersDivisible() {

        System.out.println("Введите число от 1 до 20");

        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) {
                continue;
            }
            System.out.println(i);
        }
    }

    public static void positiveNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число, для завершения программы введите 0");

        while (true) {
            int console = scanner.nextInt();

            if (console < 0) {
                System.out.println("Вы ввели отрицательное число, введите положительное, для завершения программы введите 0");

                continue;
            }
            if (console == 0) {
                break;
            }
            System.out.println(console);
        }

    }

    public static void inputString() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст, если хотите завершить программу введите слово \"stop\"");

        while (true) {
            String console = scanner.nextLine();
            if (console.equals("exit")) {
                System.out.println("Программа завершается");
                break;
            }
        }

    }
}