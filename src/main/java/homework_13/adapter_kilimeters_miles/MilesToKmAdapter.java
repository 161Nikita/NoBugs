package homework_13.adapter_kilimeters_miles;

public class MilesToKmAdapter implements KilometersTarget{
    private final LegacyMilesSystem milesSystem;
    private static final double milesToKmCoefficient = 1.60934;

    public MilesToKmAdapter(LegacyMilesSystem milesSystem) {
        this.milesSystem = milesSystem;
    }

    @Override
    public void showDistanceInKm(double km) {
        double miles = milesSystem.getDistanceInMiles();

        double convertedKm = miles * milesToKmCoefficient;
        System.out.println("[Adapter]; Получено в милях " + miles);
        System.out.println("[Adapter]; После конвертации " + convertedKm);
    }
}
