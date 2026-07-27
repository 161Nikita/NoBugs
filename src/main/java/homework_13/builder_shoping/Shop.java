package homework_13.builder_shoping;

import java.util.Arrays;

public class Shop {
    private final Order.OrderBuilder builder;

    public Shop(Order.OrderBuilder builder) {
        this.builder = builder;
    }

    public Order constructOrderWithDetails() {
        return builder.setItems(Arrays.asList("Laptop", "Keys", "PowerBank"))
                .setDiscount(10)
                .setPaymentMethod("картой онлайн")
                .setDeliveryAddress("СПб, ул. Ленина, д. 1")
                .build();
    }

    public Order constructSimpleOrder() {
        return builder.setItems(Arrays.asList("Laptop"))
                .setDiscount(0.0)
                .setPaymentMethod("Наличные при получении")
                .setDeliveryAddress("Самовывоз")
                .build();
    }
}
