package homework_10;

public class DebugTask8 {
    public static void main(String[] args) {
        double a = 0.1 * 3;
        double b = 0.3;
        double c = 0.001;
        if (Math.abs(a - b) < c) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }
}
