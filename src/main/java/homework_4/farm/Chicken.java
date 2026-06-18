package homework_4.farm;

public class Chicken implements FarmAnimal {

    @Override
    public void feed() {
        System.out.println("ест зерно");
    }

    @Override
    public void care() {
        System.out.println("нуждается в кормушке");
    }

    @Override
    public void produce() {
        System.out.println("несет яйца");
    }
}
