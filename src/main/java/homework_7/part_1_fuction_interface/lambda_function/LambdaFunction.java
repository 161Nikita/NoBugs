package homework_7.part_1_fuction_interface.lambda_function;


import java.util.function.Function;

public class LambdaFunction {

    public static void main(String[] args) {

        Function<String, Integer> function = str -> str.length();

        System.out.println(function.apply("Nikita"));
        System.out.println(function.apply("Anna"));
    }
}
