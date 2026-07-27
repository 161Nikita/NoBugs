package homework_mock.algos.task_algos;

/**
 * Удаление всех пробелов из строки. Удалить все пробелы из строки, включая в начале, в середине и в конце
 */

public class RemoveSpaces {

    public static String removeSpaces(String str) {

        if (str == null){
            return null;
        }
        return str.replaceAll("\\s+", "");

    }

    public static void main(String[] args) {
        System.out.println(removeSpaces(" Привет              как дела "));
        System.out.println(removeSpaces("Текст\tс\nтабом\rи\nпереносом"));
    }
}
