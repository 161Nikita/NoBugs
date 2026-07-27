package homework_mock.algos.task_algos;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Проверка корректности скобочной последовательности. Проверить, сбалансированы ли круглые скобки в строке.
 * <p>
 * () - true
 * (()) - true
 * )( - false
 * ()) - false
 * ((()) - false
 * ( - false
 * ) - false
 */

public class IsBalanced {

    public static boolean isBalanced(String s) {

        if (s == null) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '('){
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

        System.out.println(isBalanced("()"));
        System.out.println(isBalanced("(()"));
        System.out.println(isBalanced("())"));
        System.out.println(isBalanced(")("));
        System.out.println(isBalanced("(()())"));
        System.out.println(isBalanced("(()(())"));
        System.out.println(isBalanced(")"));
        System.out.println(isBalanced("("));
    }
}
