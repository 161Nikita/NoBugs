package practice_8_func_lambda.funcinterfaces;

public class MainForMathOperation {
    public static void main(String[] args) {
        MathOperation add = (x, y) -> x + y;
        MathOperation subtract = (x, y) -> x - y;
        MathOperation multiply = (x, y) -> x * y;
        MathOperation divide = (x, y) -> x / y;

        System.out.println(add.aplly(2, 3));
        System.out.println(subtract.aplly(10, 7));
        System.out.println(multiply.aplly(8, 3));
        System.out.println(divide.aplly(15, 5));
    }
}
