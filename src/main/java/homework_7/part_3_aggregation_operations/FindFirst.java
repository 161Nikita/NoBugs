package homework_7.part_3_aggregation_operations;

import java.util.Arrays;
import java.util.List;

public class FindFirst {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Никита", "Бритва", "Борис", "Мегабук", "Абрикос");

        List<String> firstString = list.stream()
                .filter(str -> str.startsWith("Б"))
                .findFirst()
                .stream().toList();

        System.out.println(firstString);
    }
}
