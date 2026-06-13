package practice_10_tests;

public class StringProcessor {

    // Метод для переворота строки
    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Метод для проверки, является ли строка палиндромом
     * Примеры:
     *   - "abba" -> true
     *   - "bba" -> false
     */
    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equals(reversed);
    }
}
