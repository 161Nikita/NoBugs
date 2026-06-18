package homework_4.zoo;

public class Bird extends Animal {

    public Bird() {
        super("ПТИЦА", "чирикает", "летает");
    }

    @Override
    public void demonstration() {
        System.out.println("Птица чирикает и летает");
    }
}
