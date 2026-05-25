package practice_4.solvers;

public class IfTaskSolver {
    public static void main(String[] args) {
        // проверка метода четности
        System.out.println(checkParity(3));
        System.out.println(checkParity(4));

        // проверка метода определения возраста
        System.out.println(checkAge(4));
        System.out.println(checkAge(21));
        System.out.println(checkAge(71));

        // проверка нахождения максимально числа среди 3
        System.out.println(checkMax(4, 5, 122));
    }
    /**
     * Метод для проверки четности числа
     *
     * @param number
     * @return
     */
    public static String checkParity(int number) {

        String parity = "Нечетное";

        if (number % 2 == 0) {
            parity = "Четное";
        }
        return parity;
    }

    /**
     * Метод для проверки возраста
     */

    public static String checkAge(int age) {
        String ageDescriotion = "";
        if (age < 18) {
            ageDescriotion = "Несовершеннолетний";
        }
        if (age >= 18 && age <= 60) {
            ageDescriotion = "Взрослый";
        }
        if (age > 60) {
            ageDescriotion = "Пожилой";
        }
        return ageDescriotion;
    }
    /**
     * Проверка наибольшего числа
     */

    public static int checkMax(int a, int b, int c) {
        int maxB = b;
        if (a > b) {
            maxB = a;
        }
        int max = maxB;
        if (c > maxB) {
            max = c;
        }
        return max;
    }
}
