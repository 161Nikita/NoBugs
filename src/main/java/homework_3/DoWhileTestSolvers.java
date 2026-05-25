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

        int console;

        do {
            System.out.println("Введите положительное число");
            console = scanner.nextInt();
            if (console < 0) {
                System.out.println("Вы ввели отрицательное число, попробуйте снова");
            }
        }
        while (console < 0);
        {
            System.out.println("Поздравляю, вы ввели положительное число");
        }
    }

    public static void checkPassword() {
        Scanner scanner = new Scanner(System.in);

        String password = "Nikita123";
        String console;

        do {
            System.out.println("Введите пароль");
            console = scanner.nextLine();
            if (!console.equals(password)) {
                System.out.println("Вы ввели неверный пароль, попробуйте снова");
            }
        } while (!console.equals(password));
        {
            System.out.println("Вы ввели верный пароль!");
        }
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

        String command = "exit";

        String console;


        do {
            System.out.println("Введите команду для завершения программы");
            console = scanner.nextLine();
            if (!console.equals(command)) {
                System.out.println("Вы ввели неверную команду, попробуйте снова");
            }
        } while (!console.equals(command));
        {
            System.out.println("Вы ввели верную команду, программа завершает работу");
        }
    }

    public static void countingNumber() {

        Scanner scanner = new Scanner(System.in);

        int count = 0;

        System.out.println("Введите число");
        int console = scanner.nextInt();
        do {
            count++;
            console = console / 10;

        } while (console > 0);
        {
            System.out.println("Количество цифр " + count);
        }


    }
}