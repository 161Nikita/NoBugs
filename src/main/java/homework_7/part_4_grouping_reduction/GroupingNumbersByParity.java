package homework_7.part_4_grouping_reduction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupingNumbersByParity {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        String collect = list.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0))
                .toString();

        System.out.println(collect);
    }
}
