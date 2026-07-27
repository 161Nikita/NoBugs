package homework_13.builder_shoping;

import java.util.List;

public class Order {
    private final List<String> items;
    private final double discount;
    private final String paymentMethod;
    private final String deliveryAddress;

    private Order(OrderBuilder builder) {
        this.items = builder.items;
        this.discount = builder.discount;
        this.paymentMethod = builder.paymentMethod;
        this.deliveryAddress = builder.deliveryAddress;
    }

    public List<String> getItems() {
        return items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getDiscount() {
        return discount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", discount=" + discount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

    public static class OrderBuilder {
        private List<String> items;
        private double discount = 0.0;
        private String paymentMethod = "Наличные при получении";
        private String deliveryAddress = "Самовывоз";

        public OrderBuilder setItems(List<String> items) {
            this.items = items;
            return this;
        }

        public OrderBuilder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public OrderBuilder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}