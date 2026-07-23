package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Конкатенация строк через StringBuilder. Собрать строку из массива слов, разделяя пробелами
 */

public class JoinStringBuilder {

    public static String joinString(String[] words) {

        if (words == null) {
            throw new IllegalArgumentException("В качестве аргумента null не должен передаваться");
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

       String[] array1 = {"Привет, как дела?"};
       String[] array2 = {"Привет, 123"};
       String[] array3 = {"Привет"};
       String[] array4 = {};

        System.out.println(joinString(array1)); // Привет, как дела?
        System.out.println(joinString(array2)); //Привет, 123
        System.out.println(joinString(array3)); // Привет
        System.out.println(joinString(array4)); // ""

        try {
            joinString(null);
        } catch (IllegalArgumentException e){
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}
