package homework_1;

public class Product {

    String name;
    int price;

    Product(String someName, int somePrice) {
        this.name = someName;
        this.price = somePrice;
    }

    String getName() {
        return this.name;
    }
     int getPrice() {
        return this.price;
     }

     void setPrice(int newPrice) {
        this.price = newPrice;
     }

     void applyDiscount(int discount) {
        price -= discount;
     }

     void printInfo() {
         System.out.println("Товар: " + this.name + ", цена: " + this.price);
     }
}
