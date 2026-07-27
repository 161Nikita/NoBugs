package homework_13.adapter_kilimeters_miles;

public class ModernKmSystem implements KilometersTarget{
    @Override
    public void showDistanceInKm(double km) {
        System.out.println("[Новая система]: Все успешно обработано " + km + " км");
    }
}
