package practice_5.restaraunt_manager;

public class Main {
    public static void main(String[] args) {
        RestarauntManager restarauntManager = new RestarauntManager();

        restarauntManager.addNewOrder("Картошка фри");
        restarauntManager.addNewOrder("Спагетти");
        restarauntManager.addNewOrder("Пицца");
        restarauntManager.printOrders();

       /* restarauntManager.addNewOrder("Спагетти");
        restarauntManager.printOrders();

        restarauntManager.getNextOrderForProcessing();
        restarauntManager.printOrders();*/

        restarauntManager.deleteOrder("Пицца");
        restarauntManager.printOrders();
    }
}
