package homework_mock.task_oop.order_9service;

public class OrderItem {

    private String name;
    private double price;
    private int quantity ;

    public OrderItem(String name, double price, int quality) {
        this.name = name;
        this.price = price;
        this.quantity  = quality;
    }

    public double getTotal(){
        return this.price * this.quantity ;
    }
}
