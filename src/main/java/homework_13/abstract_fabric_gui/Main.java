package homework_13.abstract_fabric_gui;

public class Main {
    public static void main(String[] args) {

        System.out.println("---WINDOWS---");

        GUIFactory windowsFactory = new WindowsFactory();

        Button winButton = windowsFactory.createButton();
        Window winWindow = windowsFactory.createWindow();
        Menu winMenu = windowsFactory.createMenu();

        winButton.render();
        winWindow.open();
        winMenu.showOptions();

        System.out.println("---MacOS---");

        GUIFactory macFactory = new MacFactory();

        Button macButton = macFactory.createButton();
        Window macWindow = macFactory.createWindow();
        Menu macMenu = macFactory.createMenu();

        macButton.render();
        macWindow.open();
        macMenu.showOptions();
    }
}
