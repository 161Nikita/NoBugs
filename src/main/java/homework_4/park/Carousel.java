package homework_4.park;

public class Carousel implements Attraction{

    @Override
    public String info() {
        return "спокойный аттракцион";
    }

    @Override
    public void maintain() {
        System.out.println("тех. обслуживание");
    }
}
