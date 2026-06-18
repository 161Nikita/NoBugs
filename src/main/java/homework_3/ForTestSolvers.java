package homework_3;

import java.util.Scanner;

public class ForTestSolvers {

    public static void main(String[] args) {
        // проверка метода Вывод чисел от 1 до 100, делящихся на 3
        //outputNumbers();
        // проверка метода Сумма чисел от 1 до n
        //System.out.println(sumNumber());
        // проверка метода Таблица умножения для числа
        //tableMultiply();
        // проверка метода Проверка на простое число
        //System.out.println(checkSimpleNumber());
        // проверка метода Вывод чисел от 1 до 10
        //outputNum();


    }

    public static void outputNumbers() {

        for (int i = 1; i < 100; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
            }
        }
    }

    public static int sumNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число: от 1");
        int consol = scanner.nextInt();
        int sum = 0;

        for (int i = 0; i <= consol; i++) {
            sum = sum + i;
        }
        return sum;
    }

    public static void tableMultiply() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число");

        int console = scanner.nextInt();

        for (int i = 0; i <= 10; i++) {

            System.out.println(console + " * " + i + " = " + console * i);
        }
    }

    public static boolean checkSimpleNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число: ");

        int console = scanner.nextInt();

        boolean isPrime = true;

        for (int i = 2; i < console; i++) {
            if (console % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static void outputNum() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }

}