package homework_13.fabric_method_vehicle;

public abstract class VehicleFactory {

    public abstract Vehicle createVehicle();

    public void printSpeed() {
        createVehicle().drive();
    }
}
