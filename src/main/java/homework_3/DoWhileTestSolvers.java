package homework_3;


import java.util.Scanner;

public class DoWhileTestSolvers {
    public static void main(String[] args) {
        // проверка метода на запрос положительного числа
        //requestPositiveNumber();
        // проверка метода валидного пароля
        //checkPassword();
        // проверка метода вывода чисел от 1 до 10
        //outputNumbers();
        // проверка метода по команде "exit"
        //commandExit();
        // проверка метода на подсчет количества цифр в числе
        //countingNumber();

    }

    public static void requestPositiveNumber() {
        Scanner scanner = new Scanner(System.in);

        int number;

        do {
            System.out.println("Введите положительное число");
            number = scanner.nextInt();
            if (number < 0) {
                System.out.println("Вы ввели отрицательное число, попробуйте снова");
            }
        }
        while (number < 0);
        System.out.println("Поздравляю, вы ввели положительное число");
    }

    public static void checkPassword() {
        Scanner scanner = new Scanner(System.in);

        String correctPassword = "Nikita123";
        String password;

        do {
            System.out.println("Введите пароль");
            password = scanner.nextLine();
            if (!password.equals(correctPassword)) {
                System.out.println("Вы ввели неверный пароль, попробуйте снова");
            }
        } while (!password.equals(correctPassword));
        System.out.println("Вы ввели верный пароль!");
    }

    public static void outputNumbers() {

        int i = 0;

        do {
            i++;
            if (i <= 10) {
                System.out.println(i);

            }
        } while (i <= 10);
    }

    public static void commandExit() {
        Scanner scanner = new Scanner(System.in);

        String exitCommand = "exit";

        String command;


        do {
            System.out.println("Введите команду для завершения программы");
            command = scanner.nextLine();
            if (!command.equals(exitCommand)) {
                System.out.println("Вы ввели неверную команду, попробуйте снова");
            }
        } while (!command.equals(exitCommand));
        System.out.println("Вы ввели верную команду, программа завершает работу");
    }

    public static void countingNumber() {

        Scanner scanner = new Scanner(System.in);

        int count = 0;

        System.out.println("Введите число");
        int input = scanner.nextInt();
        do {
            count++;
            input = input / 10;

        } while (input > 0);
        System.out.println("Количество цифр " + count);
    }
}