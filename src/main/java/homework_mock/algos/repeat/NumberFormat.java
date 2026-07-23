package homework_mock.algos.repeat;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Форматированный вывод чисел
 */

public class NumberFormat {

    public static String numFormate(double num) {

        return String.format(Locale.FRANCE,"%.2f", num);
    }

    public static String formateDecimal(double num, RoundingMode roundingMode) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00" , symbols);
        df.setRoundingMode(roundingMode);
        return df.format(num);
    }

    public static void main(String[] args) {

        double num1 = 3.151;

        System.out.println(numFormate(num1)); // 3,15
        System.out.println(formateDecimal(num1, RoundingMode.DOWN)); // 3.15
        System.out.println(formateDecimal(num1, RoundingMode.UP)); // 3.16
    }
}
