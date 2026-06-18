package homework_4.aquarium;

public class Aquarium {

    private SeaCreature creature;

    public void setCreature(SeaCreature sc) {
        this.creature = sc;
    }

    public void showMovement() {
        this.creature.move();
    }
}
