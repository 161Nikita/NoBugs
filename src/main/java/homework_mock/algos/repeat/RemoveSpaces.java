package homework_mock.algos.repeat;

/**
 * Удаление всех пробелов из строки. Удалить все пробелы их строки, включая в начале, в середине и в конце
 */

public class RemoveSpaces {

    public static String removeSpaces(String str) {

        if (str == null) {
            return null;
        }
        return str.replaceAll("\\s+" , "");
    }

    public static void main(String[] args) {
        System.out.println(removeSpaces("Привет как дела?"));
        System.out.println(removeSpaces("Привет\tHI"));
    }
}
