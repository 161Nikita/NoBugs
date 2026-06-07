package practice_8.funcinterfaces;


public class MainForChecker {
    public static void main(String[] args) {
        Checker isPisitive = n -> n > 0;

        isPisitive.printIfValid(5);
        isPisitive.printIfValid(-1);
    }
}
