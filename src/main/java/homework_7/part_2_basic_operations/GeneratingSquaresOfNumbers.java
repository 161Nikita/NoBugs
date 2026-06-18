package homework_7.part_2_basic_operations;


import java.util.Arrays;
import java.util.List;

public class GeneratingSquaresOfNumbers {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        List<Integer> squaresNumbers = list.stream()
                .map(i -> i*i)
                .toList();

        System.out.println(squaresNumbers);
    }
}
