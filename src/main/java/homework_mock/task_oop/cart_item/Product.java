package homework_mock.task_oop.cart_item;

public class Product {
    private String name;
    private double price;
    private String sku;

    public Product(String name, double price, String sku) {
        this.name = name;
        this.price = price;
        this.sku = sku;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getSku() { return sku; }
}
