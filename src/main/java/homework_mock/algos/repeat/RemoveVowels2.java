package homework_mock.algos.repeat;

/**
 * Удалить гласные. Удалить все гласные из строки.
 * <p>
 * "Привет" - "Првт"
 * "Ёлка" - "лк"
 * "Ёоу" - ""
 * "" - ""
 * null - null
 */

public class RemoveVowels2 {

    public static String removeVowels(String str) {

        if (str == null) {
            return null;
        }

        String clean = str.replaceAll("(?iu)[eyuioaёуеыаоэяию]", "");
        return clean;
    }

    public static void main(String[] args) {
        System.out.println(removeVowels("Привет")); // Првт
        System.out.println(removeVowels("Ёлка")); // лк
        System.out.println(removeVowels("Ёоу")); // ""
        System.out.println(removeVowels("")); // ""
        System.out.println(removeVowels(null)); // null
    }
}