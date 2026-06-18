package homework_4.pet;

public class Cat extends Pet {
    @Override
    public void feed() {
        System.out.println("ест влажный корм");
    }

    @Override
    public void interact() {
        System.out.println("играет");
    }
}
