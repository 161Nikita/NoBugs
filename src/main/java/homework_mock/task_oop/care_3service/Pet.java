package homework_mock.task_oop.care_3service;

public abstract class Pet {

    private final String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void eat();
    public abstract void sleep();

}
