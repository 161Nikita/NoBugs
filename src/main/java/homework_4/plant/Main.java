package homework_4.plant;

public class Main {
    public static void main(String[] args) {
        BotanicalGarden botanicalGarden = new BotanicalGarden();

        Plant orchid = new Orchid();
        Plant cactus = new Cactus();

        botanicalGarden.setPlant(orchid);
        botanicalGarden.maintainPlant();

        botanicalGarden.setPlant(cactus);
        botanicalGarden.maintainPlant();
    }
}
