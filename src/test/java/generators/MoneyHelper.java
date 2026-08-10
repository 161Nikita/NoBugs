package generators;

public class MoneyHelper {
    // Статический метод для округления до копеек
    public static double round(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
