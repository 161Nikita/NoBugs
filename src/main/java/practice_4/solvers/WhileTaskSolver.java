package practice_4.solvers;

import java.util.Scanner;

public class WhileTaskSolver {
    public static void main(String[] args) {
        // проверка метода распечатке всех чисел от 1 до 10
        //printAllNumbersBefore10();
        // проверка программы по считыванию команд пока не введут "exit"
        //commandReader();
        // проверка метода по подсчету сумм цифр в числе
        System.out.println(sumOfDigits(100));
    }

    public static void printAllNumbersBefore10() {
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
    }

    public static void commandReader() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.print("Введите команду");
            command = scanner.nextLine();
        }
        System.out.println("Программа завершена");
    }

    public static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum = sum + number % 10;
            number = number / 10;
        }
        return sum;
    }
}
