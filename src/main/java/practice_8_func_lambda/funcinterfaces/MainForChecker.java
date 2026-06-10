package practice_8_func_lambda.funcinterfaces;


public class MainForChecker {
    public static void main(String[] args) {
        Checker isPisitive = n -> n > 0;

        isPisitive.printIfValid(5);
        isPisitive.printIfValid(-1);
    }
}
