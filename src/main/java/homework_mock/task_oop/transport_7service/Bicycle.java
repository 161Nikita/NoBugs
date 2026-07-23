package homework_mock.task_oop.transport_7service;

public class Bicycle extends Transport{
    public Bicycle(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Велосипед " + getName() + " крутит педали");
    }
}
