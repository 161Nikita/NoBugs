package homework_4.farm;

public class Cow implements FarmAnimal {
    @Override
    public void feed() {
        System.out.println("ест траву");
    }

    @Override
    public void care() {
        System.out.println("выпас");
    }

    @Override
    public void produce() {
        System.out.println("дает молоко");
    }
}
