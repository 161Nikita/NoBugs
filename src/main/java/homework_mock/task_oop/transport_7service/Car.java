package homework_mock.task_oop.transport_7service;

public class Car extends Transport{
    public Car(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Машина " + getName() + " едет по дороге");
    }
}
