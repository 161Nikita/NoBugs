package homework_mock.algos.task_algos;

/**
 * Сумма чисел в строке. Найти сумму всех чисел, встречающихся в строке
 * <p>
 * "12, 11" - 23
 */

public class SumNumInString {

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
        System.out.println(sumNum("12,     11"));// 23
        System.out.println(sumNum("asd12,asd11")); // 23
        System.out.println(sumNum(null));
    }
}
