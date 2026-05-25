package homework_3;

import java.util.Scanner;

public class WhileTestSolvers {

    public static void main(String[] args) {

        // проверка метода на вычисления факториала
        //System.out.println(calculatingTheFactorial());
        // проверка метода на вывод чётных чисел до заданного
        //printEvenNumbers();
        // проверка метода на вывод обратного отсчета от введённого до 1
        //countdown();


    }

    public static int calculatingTheFactorial() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число от 1");
        int console = scanner.nextInt();

        int i = 1;
        int result = 1;

        while (i <= console) {

            result = result * i;
            i++;
        }
        return result;
    }

    public static void printEvenNumbers() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число от 1");
        int console = scanner.nextInt();
        int i = 1;

        while (i < console) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
            i++;
        }
    }

    public static void countdown() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число больше 1");
        int console = scanner.nextInt();

        while (console >= 1) {

            System.out.println(console);
            console--;
        }
    }
}
