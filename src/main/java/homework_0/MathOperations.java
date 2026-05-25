package homework_0;

public class MathOperations {

    public static void main(String[] args) {
        System.out.println(add(3, 3)); // вызываем add
        System.out.println(subtract(3, 2)); // вызываем subtract
        System.out.println(multiply(3, 2)); // вызываем multiply
        System.out.println(divide(3, 2)); // вызываем divide
        System.out.println(findMax(56, 46)); // вызываем findMax - нахождение максимального числа
        System.out.println(difference(1, 2)); // вызываем difference - нахождение разницы между двумя числами
        System.out.println(squareArea(2)); // вызываем squareArea - нахождение площади квадрата
        System.out.println(squarePerimeter(4)); // вызываем squarePerimeter - нахождение периметра квадрата
        System.out.println(convertSecondsToMinutes(30)); // вызываем convertSecondsToMinutes - перевод секунд в минуты
        System.out.println(averageSpeed(100, 50)); // вызываем averageSpeed - вычисления средней скорости
        System.out.println(findHypotenuse(3, 3)); // вызываем findHypotenuse - для вычисления гипотенузы
        System.out.println(circleCircumference(3)); // вызываем circleCircumference - для вычисления длины окружности
        System.out.println(calculatePercentage(3, 6)); // вызываем calculatePercentage - для вычисления процентов
        System.out.println(celsiusToFahrenheit(3)); // вызываем celsiusToFahrenheit - для перевода в Фаренгейты
        System.out.println(fahrenheitToCelsius(300)); // вызываем fahrenheitToCelsius - для перевода в Цельсий
    }

    // метод возвращает сумму двух чисел
    static int add(int x, int y) {
        return x + y;
    }

    // метод возвращает разницу двух числе
    static int subtract(int x, int y) {
        return x - y;
    }

    // метод возвращает произведение двух чисел (умножение)
    static int multiply(int x, int y) {
        return x * y;
    }

    // метод возвращает результат деления двух чисел (double)
    static double divide(int x, int y) {
        return (double) x / y;
    }

    static int findMax(int a, int b) {
        return Math.max(a, b);
    }

    static int difference(int x, int y) {
        return Math.abs(x - y);
    }

    // метод возвращает площадь квадрата
    static int squareArea(int side) {
        return side * side;
    }

    // метод возвращает периметр квадрата
    static int squarePerimeter(int side) {
        return 4 * side;
    }

    // метод возвращает перевод секунды в минуты
    static double convertSecondsToMinutes(int seconds) {
        return (double) seconds / 60.0;
    }

    // метод вычисления средней скорости
    static double averageSpeed(double distance, double time) {
        return distance / time;
    }

    // метод для нахождения гипотенузы
    static double findHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    // метод для длины окружности
    static double circleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    // метод для вычисления процентов
    static double calculatePercentage(double total, double part) {
        return (part / total) * 100;
    }

    // метод для перевода температуры в Фаренгейты
    static double celsiusToFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }

    // метод для перевода температуры в Цельсий
    static double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }
}