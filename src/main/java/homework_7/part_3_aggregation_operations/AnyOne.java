package homework_7.part_3_aggregation_operations;

import java.util.Arrays;
import java.util.List;

public class AnyOne {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 3, 5, 6);

        boolean anyOne = list.stream()
                .anyMatch(i -> i % 2 == 0);

        System.out.println(anyOne);
    }
}
