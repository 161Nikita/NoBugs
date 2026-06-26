package homework_mock;


/*
Проверка корректности скобочной последовательности ()
 */
public class Balanced {

    public static boolean isBalanced(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }

    public static void main(String[] args) {

        String[] array = {"()", ")(", "))", "((", "(Nikita) = (super)", "(((()))"};

        for (String str : array) {
            System.out.println("Строка: " + str + " Результат: " + isBalanced(str));
        }
    }
}