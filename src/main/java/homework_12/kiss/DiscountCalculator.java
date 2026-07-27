package homework_12.kiss;

/*
Нарушение KISS (Keep It Simple, Stupid) – чрезмерно сложный код
Задача: Упростите код, убрав вложенные условия, сделав его более читаемым и поддерживаемым.
 */

public class DiscountCalculator {
    public static double calculateDiscount(double price, boolean isLoyalCustomer, boolean isFirstPurchase, boolean hasCoupon) {

        double discount = 0.02;

        if (isLoyalCustomer) {
            discount = isFirstPurchase ? 0.10 : 0.05;
        } else if (hasCoupon) {
            discount = 0.07;
        }
        return price * (1 - discount);
    }

    public static void main(String[] args) {
        System.out.println(calculateDiscount(1000, true,true,true)); // 900
        System.out.println(calculateDiscount(1000, true,false,true)); // 950
        System.out.println(calculateDiscount(1000, false,false,true)); // 930
        System.out.println(calculateDiscount(1000, false,false,false)); // 980
    }
}
