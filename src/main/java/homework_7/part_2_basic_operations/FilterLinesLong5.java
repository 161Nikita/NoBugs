package homework_7.part_2_basic_operations;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterLinesLong5 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("Nikita", "Anna", "Maria", "Denis");

        List<String> filterLinesLong5 = list.stream()
                .filter(str -> str.length() > 5)
                .collect(Collectors.toList());
        // .toList();
        System.out.println(filterLinesLong5);
    }
}
