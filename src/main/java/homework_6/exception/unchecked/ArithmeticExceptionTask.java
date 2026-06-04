package homework_6.exception.unchecked;
public class ArithmeticExceptionTask {
    public static void main(String[] args) {
        //divide(3, 3);
        divide(3, 0);
    }


    public static void divide(int a, int b) {
        try {
            int result = a / b;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Делить на ноль нельзя!");
        }
    }
    }


