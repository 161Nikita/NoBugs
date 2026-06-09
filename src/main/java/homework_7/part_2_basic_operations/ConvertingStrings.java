package homework_7.part_2_basic_operations;


import java.util.Arrays;
import java.util.List;

public class ConvertingStrings {

    public static void main(String[] args) {


        List<String> list = Arrays.asList("Nikita", "Anna", "Mariya", "Denis");

        List<Integer> converting = list.stream()
                .map(String::length)
                .toList();

        System.out.println(converting);

    }
}
