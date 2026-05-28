package homework_4.park;

public class AmusementPark {
    private Attraction attraction;

    public void setAttraction(Attraction a) {
        this.attraction = a;
    }

    public void operateAttraction() {
        System.out.println("Ощущение: " + this.attraction.info());
        this.attraction.maintain();
    }
}
