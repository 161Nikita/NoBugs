package homework_4.pet;

public class Main {
    public static void main(String[] args) {

        Pet dog = new Dog();
        Pet cat = new Cat();

        PetManager petManager = new PetManager();

        petManager.setPet(dog);
        petManager.handlePet();

        petManager.setPet(cat);
        petManager.handlePet();


    }
}
