package homework_mock;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicates {

    public static List<Integer> removeDuplicates(List<Integer> list) {

        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList());

        System.out.println(removeDuplicates(nums));
    }
}
