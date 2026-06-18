package homework_7.part_2_basic_operations;

import java.util.Arrays;
import java.util.List;

public class DeleteDublicates {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 5, 6, 1, 4);

        List<Integer> distinctNumbers = list.stream()
                .distinct()
                .toList();
        System.out.println(distinctNumbers);
    }
}
