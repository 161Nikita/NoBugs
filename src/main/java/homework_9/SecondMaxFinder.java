package homework_9;

import java.util.Arrays;

public class SecondMaxFinder {
    public int findSecondMax(int[] numbers) {
        return Arrays.stream(numbers).distinct().sorted().skip(numbers.length - 2).findFirst().orElseThrow();
    }

}
