package homework_mock.task_oop.transport_7service;

public abstract class Transport {

    private final String name;

    public Transport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void move();
}
