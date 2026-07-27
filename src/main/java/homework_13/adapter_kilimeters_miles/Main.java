package homework_13.adapter_kilimeters_miles;

public class Main {
    public static void main(String[] args) {
        System.out.println("Работа с километрами");
        KilometersTarget modernSystem = new ModernKmSystem();
        modernSystem.showDistanceInKm(70);

        LegacyMilesSystem legacyMilesSystem = new LegacyMilesSystem();

        KilometersTarget adapter = new MilesToKmAdapter(legacyMilesSystem);
        adapter.showDistanceInKm(0);

    }
}