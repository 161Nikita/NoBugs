package homework_4.park;

public class Main {
    public static void main(String[] args) {
        AmusementPark amusementPark = new AmusementPark();

        Attraction rollerCoaster = new RollerCoaster();
        Attraction carousel = new Carousel();


        amusementPark.setAttraction(rollerCoaster);
        amusementPark.operateAttraction();

        amusementPark.setAttraction(carousel);
        amusementPark.operateAttraction();

    }
}
