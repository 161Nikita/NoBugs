package homework_mock.task_oop.care_3service;

import java.util.List;

public class PetCareService {

    public void commandPetSleep(List<Pet> pet) {
        for (Pet pet1 : pet) {
            pet1.sleep();
        }
    }
    public void commandPetEat(List<Pet> pet) {
        for (Pet pet1 : pet) {
            pet1.eat();
        }
    }
}
