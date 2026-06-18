package homework_7.part_3_aggregation_operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinElement {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 66, 765);

        Integer minElement = list.stream()
                .min(Comparator.naturalOrder())
                .get();

        System.out.println(minElement);
    }
}
