package homework_13.abstract_fabric_gui;

public class MacButton implements Button{
    @Override
    public void render() {
        System.out.println("[MacOS]: нажали кнопку на Mac");
    }
}
