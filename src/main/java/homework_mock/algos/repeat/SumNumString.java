package homework_mock.algos.repeat;

/**
 * Сумма чисел в строке. Найти сумму всех чисел, встречающихся в строке
 * <p>
 * "123 ааа 5" - 128
 * "fsdf12 1" - 13
 *
 */

public class SumNumString {

    public static int sumNum(String str) {

        if (str == null) {
            return 0;
        }

        int sum = 0;
        int num = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else {
                sum += num;
                num = 0;
            }
        }
        return sum + num;
    }

    public static void main(String[] args) {
        System.out.println(sumNum("123 ааа 5")); // 128
        System.out.println(sumNum("fsdf12 1")); // 13
    }
}