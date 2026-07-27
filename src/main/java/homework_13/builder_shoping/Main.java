package homework_13.builder_shoping;


public class Main {
    public static void main(String[] args) {
        Order.OrderBuilder builder = new Order.OrderBuilder();

        Shop shop = new Shop(builder);

        Order order1 = shop.constructOrderWithDetails();
        System.out.println(order1);

        Order order2 = shop.constructSimpleOrder();
        System.out.println(order2);

    }
}