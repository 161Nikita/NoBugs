package homework_4.pet;

public class PetManager {
    private Pet pet;

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void handlePet() {
        this.pet.feed();
        this.pet.interact();
    }
}
