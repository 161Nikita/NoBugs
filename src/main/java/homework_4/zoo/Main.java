package homework_4.zoo;

public class Main {

    public static void main(String[] args) {
        Animal bird = new Bird();
        Animal elephant = new Elephant();

        Manager manager = new Manager();

        manager.addAnimal(elephant);
        manager.demonstration();
        manager.addAnimal(bird);
        manager.demonstration();


    }
}
