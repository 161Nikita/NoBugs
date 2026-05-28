package homework_4.pet;

public class Dog extends Pet {

    @Override
    public void feed() {
        System.out.println("есть сухой корм");
    }

    @Override
    public void interact() {
        System.out.println("гуляет");
    }
}
