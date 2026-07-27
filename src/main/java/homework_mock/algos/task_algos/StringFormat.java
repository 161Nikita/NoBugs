package homework_mock.algos.task_algos;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Форматированный вывод чисел. Вывести числа с 2 знаками после запятой
 * <p>
 * Вариант 1
 * String.format с фиксацией локали
 * 3.14159 -> 3.14
 * Вариант 2
 * DecimalFormat
 *
 */
public class StringFormat {

    // Вариант 1: Использование String.format с фиксацией локали
    public static String formatWithStringFormat(double number) {

        return String.format(Locale.US, "%.2f", number);
    }

    // Вариант 2: Использование DecimalFormat с явным указанием режима округления
    public static String formatWithDecimalFormat(double number, RoundingMode roundingMode) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);

        DecimalFormat df = new DecimalFormat("0.00", symbols);

       df.setRoundingMode(roundingMode);

        return df.format(number);
    }

    public static void main(String[] args) {
        double num1 = 3.14159;
        double num2 = 2.556;
        double num3 = 5.0; // Тест на "0" после запятой

        System.out.println("--- Тест 1: String.format ---");
        System.out.println(num1 + " -> " + formatWithStringFormat(num1)); // 3.14
        System.out.println(num2 + " -> " + formatWithStringFormat(num2)); // 2.56 (округлило вверх)
        System.out.println(num3 + " -> " + formatWithStringFormat(num3)); // 5.00 (добавило нули)

        System.out.println("\n--- Тест 2: DecimalFormat (Округление ВНИЗ / DOWN) ---");
        // CEILING округляет в сторону положительной бесконечности
        System.out.println("3.14159 вниз -> " + formatWithDecimalFormat(3.14159, RoundingMode.DOWN)); // 3.14
        System.out.println("2.55999 вниз -> " + formatWithDecimalFormat(2.55999, RoundingMode.DOWN)); // 2.55

        System.out.println("\n--- Тест 3: DecimalFormat (Округление ВВЕРХ / UP) ---");
        // UP округляет в большую сторону от нуля
        System.out.println("3.14159 вверх -> " + formatWithDecimalFormat(3.14159, RoundingMode.UP)); // 3.15
        System.out.println("5.00001 вверх -> " + formatWithDecimalFormat(5.00001, RoundingMode.UP)); // 5.01
    }

}
