package homework_4.restaurant;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();

        Dish soup = new HotDish(50);
        Dish sok = new Drink(200);

        menu.addDish(soup);
        menu.addDish(sok);

        menu.printMenu();
    }
}
