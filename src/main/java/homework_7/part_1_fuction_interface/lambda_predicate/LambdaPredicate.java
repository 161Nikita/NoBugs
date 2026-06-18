package homework_7.part_1_fuction_interface.lambda_predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaPredicate {
    public static void main(String[] args) {

        /*List<Integer> numbers = Arrays.asList(1, 2, 3 ,4 ,5, 6);

        List<Integer> OnlyEvenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(OnlyEvenNumbers);*/

        Predicate<Integer> evenNumbers = n -> n % 2 == 0;

        System.out.println(evenNumbers.test(2));
        System.out.println(evenNumbers.test(3));
    }
}
