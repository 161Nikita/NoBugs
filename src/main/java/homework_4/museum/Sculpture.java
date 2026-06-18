package homework_4.museum;

public class Sculpture implements Exhibit {

    @Override
    public String describe() {
        return "скульптурный объект";
    }

    @Override
    public void preserve() {
        System.out.println("реставрация");
    }
}
