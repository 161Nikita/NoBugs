package homework_1;

public class Product {

    String name;
    double price;

    Product(String someName, double somePrice) {
        this.name = someName;
        this.price = somePrice;
    }

    String getName() {
        return this.name;
    }

    double getPrice() {
        return this.price;
    }

    void setPrice(double newPrice) {
        this.price = newPrice;
    }

    void applyDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            System.out.println("Скидка не может быть меньше 0% или больше 100%");
        } else {
            price -= price * discount / 100;
        }
    }

    void printInfo() {
        System.out.println("Товар: " + this.name + ", цена: " + this.price);
    }
}