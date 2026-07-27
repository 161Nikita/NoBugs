package homework_13.fabric_method_vehicle;

public class BicycleFactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Bicycle();
    }
}
