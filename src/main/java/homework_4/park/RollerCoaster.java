package homework_4.park;

public class RollerCoaster implements Attraction {

    @Override
    public String info() {
        return "острые ощущения";
    }

    @Override
    public void maintain() {
        System.out.println("проверка безопасности");
    }
}
