package homework_mock.algos.task_algos;

/**
 * Подсчет гласных. Посчитать количество гласных в строке
 * <p>
 * Привет - 2
 * Privet - 2
 */

public class CountVowels2 {

    public static int countVowels(String str) {

        if (str == null) {
            return 0;
        }

        int count = 0;

        for (char ch : str.toLowerCase().toCharArray()) {
            if ("eyuioaуёеыаоэяию".indexOf(ch) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countVowels("Hi"));
    }
}
