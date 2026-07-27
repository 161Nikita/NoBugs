package homework_13.fabric_method_vehicle;

public class Main {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        carFactory.printSpeed();

        VehicleFactory bicycleFactory = new BicycleFactory();
        bicycleFactory.printSpeed();
    }
}
