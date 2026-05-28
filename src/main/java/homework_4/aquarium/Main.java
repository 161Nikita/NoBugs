package homework_4.aquarium;

public class Main {
    public static void main(String[] args) {

        Aquarium aquarium = new Aquarium();

        SeaCreature shark = new Shark();
        SeaCreature starfish = new Starfish();


        aquarium.setCreature(shark);
        aquarium.showMovement();


        aquarium.setCreature(starfish);
        aquarium.showMovement();

    }
}
