package practice_1;

public class HelloWorld {
    // Поля
    static  int a = 1;

    // Метод

    // Зеленая стрелка означает, что программа запускаема
    // Метод main является точкой входа в программу
    public static void main(String[] args) {
        System.out.println("Привет, мир!");
        System.out.println("Это Никита");
        System.out.println(1 + 2);
        System.out.println(3 * 5);
        System.out.println("В поле а хранится: " + a);
        int sum1 = sum(1, 3);
        System.out.println(sum1);
        System.out.println(sum(1, 4));
        int mult1 = multiply(3, 2);
        System.out.println(mult1);
        int subs1 = substruct(22, 10);
        System.out.println(subs1);
        double divide1 = divide(3, 2);
        System.out.println(divide1);
    }

    public static int sum(int x, int y) {
        //тело метода
        return x + y; // возвращаемое значение из метода
    }

    public static int multiply(int p, int k) {
        // тело метода
        int mult = p * k; // создал переменную mult и присвоил ей значение равное результату умножения p на k
        return mult;
    }

    public static int substruct(int g, int l) {
        return g - l;
    }

    public static double divide(int s, int h) {
        return (double) s / h;
    }
}
