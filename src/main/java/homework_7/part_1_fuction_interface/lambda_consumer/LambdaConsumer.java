package homework_7.part_1_fuction_interface.lambda_consumer;

import java.util.function.Consumer;

public class LambdaConsumer {
    public static void main(String[] args) {

        Consumer<String> consumer = str -> System.out.println(str);

        consumer.accept("str");
    }
}
