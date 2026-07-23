package homework_mock.task_oop.care_3service;

public class Dog extends Pet{
    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " ест мясо");
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " спит в будке");
    }
}
