package homework_7.part_2_basic_operations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNumbersMultiples5 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 3, 4, 5, 7, 8, 9, 15, 20, 30);

        List<Integer> filterMultiples5 = list.stream()
                .filter(i -> i % 5 == 0)
                //.collect(Collectors.toList());
                .toList();

        System.out.println(filterMultiples5);
    }
}
