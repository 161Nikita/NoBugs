package homework_7.part_4_grouping_reduction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupString {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Nikita", "Danil", "Ivan", "Nina", "Denis", "Anna");

        String word = list.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)))
                .toString();

        System.out.println(word);

    }
}
