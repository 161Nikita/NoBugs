package homework_13.abstract_fabric_furniture;

public class ClassicFurnitureFactory implements FurnitureFactory{
    @Override
    public Chair createChair() {
        return new ClassicChair();
    }

    @Override
    public Table createTable() {
        return new ClassicTable();
    }
}
