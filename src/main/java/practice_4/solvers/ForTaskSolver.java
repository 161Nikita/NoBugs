package practice_4.solvers;

public class ForTaskSolver {
    public static void main(String[] args) {
        // проверка таблицы умножения для числа
        //multiplyTable(5);
        // проверка суммы всех чисел до числа
        //System.out.println(sumOfAllNumbers(2));
        // проверка метода по определению простого числа
        System.out.println(checkNumberIsSimple(6));
    }

    /**
     * Метод для вывода таблицы умножения числа введенного пользователем
     *
     * @param number
     */
    public static void multiplyTable(int number) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + number * i);
        }
    }

    /**
     * Метод для вычисления суммы чисел
     */

    public static int sumOfAllNumbers(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }

    public static boolean checkNumberIsSimple(int number) {
        boolean isSimple = true;
        for (int i = 2; i <= number - 1; i++) {
            if (number % i == 0) {
                isSimple = false;
                break;
            }
        }
        return isSimple;
    }
}
