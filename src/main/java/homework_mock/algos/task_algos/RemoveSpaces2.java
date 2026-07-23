package homework_mock.algos.task_algos;

/**
 * Удалить пробелы. Удалить все пробелы из строки
 * <p>
 * "Привет всем людям планеты Земля" - "ПриветвсемлюдямпланетыЗемля"
 */

public class RemoveSpaces2 {

    public static String removeSpaces(String str) {

        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return str;
        }

        String cleaned = str.replaceAll("\\s+", "");

        return cleaned;
    }

    public static void main(String[] args) {

        System.out.println(removeSpaces("Привет всем людям планеты Земля"));
        System.out.println(removeSpaces("          "));
        System.out.println(removeSpaces(null));
    }
}