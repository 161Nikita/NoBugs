package homework_13.abstract_fabric_gui;

public class MacFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Window createWindow() {
        return new MacWindow();
    }

    @Override
    public Menu createMenu() {
        return new MacMenu();
    }
}
