package homework_4.museum;

public class Manuscriot implements Exhibit {

    @Override
    public String describe() {
        return "древний текст";
    }

    @Override
    public void preserve() {
        System.out.println("контролируемая влажность");
    }
}
