package homework_3;

import java.util.Scanner;

public class SwitchTestSolvers {

    public static void main(String[] args) {
        // проверка метода на вывод дня недели по номеру
        //System.out.println(dayOfWeek());
        // проверка метода стоимости билета по дню недели
        //System.out.println(ticketPrice());
        // проверка метода перевода числовых значений в буквенные (A-F)
        //System.out.println(rating());
        // проверка метода обработки текстовых команд
        //System.out.println(textCommand());
        // проверка метода простого калькулятора
        System.out.println(calculator());

    }

    public static String dayOfWeek() {
        Scanner scanner = new Scanner(System.in);

        String day = "";
        System.out.println("Введите число от 1 до 7");
        int console = scanner.nextInt();
        switch (console) {
            case 1:
                day = "Понедельник";
                break;
            case 2:
                day = "Вторник";
                break;
            case 3:
                day = "Среда";
                break;
            case 4:
                day = "Четверг";
                break;
            case 5:
                day = "Пятница";
                break;
            case 6:
                day = "Суббота";
                break;
            case 7:
                day = "Воскресенье";
                break;
            default:
                day = "Такого дня нет";
        }
        return day;
    }

    public static String ticketPrice() {
        Scanner scanner = new Scanner(System.in);

        String weekdays = "";

        System.out.println("Введите число, 1-5 это будние дни, 6-7 это выходные дни");
        int console = scanner.nextInt();
        switch (console) {
            case 1:
                weekdays = "Стоимость билета 300 рублей";
                break;
            case 2:
                weekdays = "Стоимость билета 300 рублей";
                break;
            case 3:
                weekdays = "Стоимость билета 300 рублей";
                break;
            case 4:
                weekdays = "Стоимость билета 300 рублей";
                break;
            case 5:
                weekdays = "Стоимость билета 300 рублей";
                break;
            case 6:
                weekdays = "Стоимость билета 450 рублей";
                break;
            case 7:
                weekdays = "Стоимость билета 450 рублей";
                break;
            default:
                weekdays = "Такого дня недели нет, введите от 1 до 7";
        }
        return weekdays;
    }

    public static String rating() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Какой у вас балл");
        int console = scanner.nextInt();
        String grade = switch (console / 10) {
            case 9, 10 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
        return grade;
    }

    public static String textCommand() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите команду из списка \"start\", \"stop\", \"restart\" или \"status\"");
        String console = scanner.nextLine();

        String statusProgram = switch (console) {
            case "start" -> "Система запущена";
            case "stop" -> "Система остановлена";
            case "restart" -> "Система перезагружается";
            case "status" -> "Система показывает статус";
            default -> "Вы ввели неправильную команду";
        };
        return statusProgram;
    }

    public static int calculator() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число: ");
        int num1 = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Введите оператор: +, -, *, /");
        String operator = scanner.nextLine();

        System.out.println("Введите второе число: ");
        int num2 = scanner.nextInt();

        int result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("Делить на ноль нельзя!");
                } result = num1 / num2;
                break;
            default:
                System.out.println("Введите правильный оператор");
                break;
        }
        return result;
    }
}
