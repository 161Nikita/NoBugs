package homework_4.farm;

public class Main {
    public static void main(String[] args) {

        Farm farm = new Farm();

        FarmAnimal cow = new Cow();
        FarmAnimal chicken = new Chicken();

        System.out.println("Корова на ферме ");
        farm.setAnimal(cow);
        farm.serviceAnimal();

        System.out.println("Курица на ферме ");
        farm.setAnimal(chicken);
        farm.serviceAnimal();
    }
}
