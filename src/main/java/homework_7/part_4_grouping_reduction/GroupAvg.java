package homework_7.part_4_grouping_reduction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAvg {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        String avgNumbers = list.stream()
                        .collect(Collectors.averagingInt(n -> n))
                                .toString();

        System.out.println(avgNumbers);
    }
}
