package homework_7.part_1_fuction_interface.funcinterfaces;

public class MainForMathOperation {

    public static void main(String[] args) {
        MathOperation add = (x, y) -> x + y;
        System.out.println(add.apply(3, 4));
        MathOperation subtract = (x, y) -> x - y;
        System.out.println(subtract.apply(3, 4));
        MathOperation multiply = (x, y) -> x * y;
        System.out.println(multiply.apply(3, 4));
        MathOperation divide = (x, y) -> x / y;
        System.out.println(divide.apply(4, 4));

    }
}