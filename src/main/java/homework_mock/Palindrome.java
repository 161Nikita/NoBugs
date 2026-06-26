package homework_mock;

public class Palindrome {
    public static boolean isPalindromeString(String s) {
        if (s == null) return false;
        String clean = s.replaceAll("\\s+", "").toLowerCase();
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }

    public static boolean isPalindromeInt(int n) {
        // Отрицательные числа и числа, оканчивающиеся на 0 (кроме самого 0), не палиндромы
        if (n < 0 || (n % 10 == 0 && n != 0)) return false;

        int original = n;
        int reversed = 0;
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return original == reversed;
    }

    public static void main(String[] args) {
        System.out.println("Строка 'шалаш' (true): " + isPalindromeString("шалаш"));
        System.out.println("Строка 'Азор' (true): " + isPalindromeString("А роза упала на лапу Азора"));
        System.out.println("Число 12321 (true): " + isPalindromeInt(12321));
        System.out.println("Число -121 (false): " + isPalindromeInt(-121));
    }
}
