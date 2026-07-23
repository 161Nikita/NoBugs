package homework_mock.algos.task_algos;

import java.util.Random;

/**
 * Генерация случайного числа от 1 до 10.
 */

public class RandomInt {

    public static int randomInt() {
        return new Random().nextInt(10)+1;
    }

    public static void main(String[] args) {
        System.out.println(randomInt());
    }
}
