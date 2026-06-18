package homework_4.restaurant;

public class Drink implements Dish {

    private int volume;

    public Drink(int volume) {
        this.volume = volume;
    }

    @Override
    public String getDescription() {
        return "сок с объемом " + volume + " мл";
    }
}
