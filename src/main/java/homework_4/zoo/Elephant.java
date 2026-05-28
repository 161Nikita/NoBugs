package homework_4.zoo;

public class Elephant extends Animal {

    public Elephant() {
        super("СЛОН", "трубит", "ходит");
    }

    @Override
    public void demonstration() {
        System.out.println("Слон трубит и ходит");
    }
}
