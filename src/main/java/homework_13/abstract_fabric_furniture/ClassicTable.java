package homework_13.abstract_fabric_furniture;

public class ClassicTable implements Table{
    @Override
    public void putOn() {
        System.out.println("Вы положили на классический стол");
    }
}
