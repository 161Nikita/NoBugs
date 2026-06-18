package homework_7.part_3_aggregation_operations;

import java.util.Arrays;
import java.util.List;

public class SumElements {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        Integer sumNumbers = numbers.stream()
                .mapToInt(n -> n)
                .sum();

        System.out.println(sumNumbers);
    }
}
