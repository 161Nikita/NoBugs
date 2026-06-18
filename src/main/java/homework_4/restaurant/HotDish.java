package homework_4.restaurant;

public class HotDish implements Dish {

    private int temperature;

    public HotDish(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getDescription() {
        return "суп с температурой " +  temperature + " градусов";
    }
}
