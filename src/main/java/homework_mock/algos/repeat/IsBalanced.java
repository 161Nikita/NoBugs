package homework_mock.algos.repeat;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Проверка корректности скобочной последовательности. Проверить, сбалансированы ли круглые скобки в строке
 * - Использовать стек
 * <p>
 * () -> true
 * ()() -> true
 * ((())) - true
 * )( -> false
 * )()( - false
 */

public class IsBalanced {

    public static boolean isBalanced(String str) {

        if (str == null || str.isEmpty()) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println(isBalanced("()")); // true
        System.out.println(isBalanced("()()")); // true
        System.out.println(isBalanced("((()))")); // true
        System.out.println(isBalanced(")(")); // false
        System.out.println(isBalanced(")()(")); // false
        System.out.println(isBalanced("")); // false
        System.out.println(isBalanced(null)); // false
    }
}