package homework_mock.algos.repeat;

/**
 * Конкатенация строк через StringBuilder. Собрать строку из массива слов, разделяя пробелами.
 * <p>
 * [Привет, как дела?", "Привет! Все хорошо, а у тебя?] -> Привет, как дела?", "Привет! Все хорошо, а у тебя?
 * [Привет] -> Привет
 * [] ->
 * [null] -> IllegalArgumentException
 */

public class JoinStringBuilder {

    public static String joinString(String[] words) {

        if (words == null) {
            throw new IllegalArgumentException("В качестве аргумента null недопустим");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (i != words.length - 1)
                sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String[] arr1 = {"Привет, как дела?", "Привет! Все хорошо, а у тебя?"};
        String[] arr2 = {"Привет"};
        String[] emptyArr = {};

        System.out.println(joinString(arr1));
        System.out.println(joinString(arr2));
        System.out.println(joinString(emptyArr));

        try {
            joinString(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}