package homework_mock.task_oop.care_3service;

public class Cat extends Pet{

    public Cat(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " ест рыбу");
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " спит в пещере");
    }
}
