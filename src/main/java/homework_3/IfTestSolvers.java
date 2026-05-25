package homework_3;

import java.util.Scanner;

public class IfTestSolvers {

    public static void main(String[] args) {
        // проверка метода на определение знака числа
        //numberSign();
        // проверка метода поиска наибольшего из двух чисел
        //maxNumber();
        // проверка метода на вывод текстового описания оценки по шкале от 1-5
        //ratingOutput();
        // проверка метода числа на чётность
        //checkParity();
        // проверка метода для определения размера скидки по возрасту
        //sizeDiscount();
        // проверка метода на вывод текстового результата по баллам
        testResult();
    }

    public static void numberSign() {
        Scanner scanner = new Scanner(System.in);
        int console;
        System.out.println("Введите число: ");
        console = scanner.nextInt();
        if (console > 0) {
            System.out.println("Число положительное");
        } else if (console < 0) {
            System.out.println("Число отрицательное");
        } else {
            System.out.println("Число равно нулю");
        }
    }

    public static void maxNumber() {
        Scanner scanner = new Scanner(System.in);

        int a;
        int b;
        System.out.println("Введите первое число: ");
        a = scanner.nextInt();
        System.out.println("Введите второе число: ");
        b = scanner.nextInt();
        if (a > b) {
            System.out.println("Наибольшее число: " + a);
        } else {
            System.out.println("Наибольшее число: " + b);
        }
    }

    public static void ratingOutput() {
        Scanner scanner = new Scanner(System.in);

        int console;
        System.out.println("Введите оценку от 1 до 5: ");
        console = scanner.nextInt();
        if (console == 1 || console == 2) {
            System.out.println("Неудовлетворительно");
        }
        if (console == 3) {
            System.out.println("Удовлетворительно");
        }
        if (console == 4) {
            System.out.println("Хорошо");
        }
        if (console == 5) {
            System.out.println("Отлично");
        }
    }

    public static void checkParity() {
        Scanner scanner = new Scanner(System.in);

        int console;

        System.out.println("Введите число: ");
        console = scanner.nextInt();
        if (console % 2 == 0) {
            System.out.println("Число чётное");
        } else {
            System.out.println("Число нечётное");
        }
    }

    public static void sizeDiscount() {
        Scanner scanner = new Scanner(System.in);

        int console;
        System.out.println("Введите ваш возраст: ");
        console = scanner.nextInt();
        if (console < 18) {
            System.out.println("Ваша скидка 25% ");
        } else if (console >= 65) {
            System.out.println("Ваша скидка 30% ");
        } else {
            System.out.println("У вас нет скидки!");
        }
    }

    public static void testResult() {
        Scanner scanner = new Scanner(System.in);

        int console;

        System.out.println("Введите число от 0 до 100");
        console = scanner.nextInt();
        if (console >= 90) {
            System.out.println("Отлично");
        }
        if (console >= 75 && console <= 89) {
            System.out.println("Хорошо");
        }
        if (console >= 60 && console <= 74) {
            System.out.println("Удовлетворительно");
        } else if (console < 60) {
            System.out.println("Неудовлетворительно");
        }
    }
}