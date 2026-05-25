package homework_1;

public class Laptop {
    String brand;
    int price;

    Laptop(String someBrand, int somePrice) {
        this.brand = someBrand;
        this.price = somePrice;
    }

    String getBrand() {
        return this.brand;
    }

    int getPrice() {
        return this.price;
    }

    void setBrand(String newBrand) {
        this.brand = newBrand;
    }

    void setPrice(int newPrice) {
        this.price = newPrice;
    }

    void printInfo() {
        System.out.println("Ноутбук: " + this.brand + ", Цена: " + this.price + " руб.");
    }
}
