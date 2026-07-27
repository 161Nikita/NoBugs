package homework_13.abstract_fabric_furniture;

public class Main {
    public static void main(String[] args) {
        System.out.println("---Это современная мебель---");

        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Table modernTable = modernFactory.createTable();

        modernChair.sitOn();
        modernTable.putOn();

        System.out.println("---Это классическая мебель---");
        FurnitureFactory classicFactory = new ClassicFurnitureFactory();
        Chair classicChair = classicFactory.createChair();
        Table classicTable = classicFactory.createTable();
        classicChair.sitOn();
        classicTable.putOn();

    }
}
