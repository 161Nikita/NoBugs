package homework_1;

public class Car {

    String brand;
    int year;

    Car(String someBrand, int someYear) {
        this.brand = someBrand;
        this.year = someYear;
    }

    String getBrand() {
        return this.brand;
    }

    int getYear() {
        return this.year;
    }

    void setBrand(String newBrand) {
        this.brand = newBrand;
    }

    void setYear(int newYear) {
        this.year = newYear;
    }

    void print() {
        System.out.println("Марка: " + this.brand + " Год выпуска: " + this.year);
    }
}
