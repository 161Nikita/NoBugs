package homework_4.plant;

public class BotanicalGarden {

    private Plant plant;

    public void setPlant(Plant p) {
        this.plant = p;
    }

    public  void maintainPlant() {
        this.plant.care();
    }
}
