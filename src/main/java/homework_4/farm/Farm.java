package homework_4.farm;

public class Farm {

    private FarmAnimal animal;

    public void setAnimal(FarmAnimal a) {
        this.animal = a;
    }

    public  void serviceAnimal() {
        this.animal.feed();
        this.animal.care();
        this.animal.produce();

    }
}
