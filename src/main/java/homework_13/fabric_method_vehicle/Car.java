package homework_13.fabric_method_vehicle;

public class Car implements Vehicle{
    @Override
    public void drive() {
        System.out.println("Автомобиль едет со скоростью 60 км/ч");
    }
}
