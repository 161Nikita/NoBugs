package homework_mock.task_oop.transport_7service;

public class Airplane extends Transport{
    public Airplane(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Самолет " + getName() + " летит по воздуху");
    }
}
