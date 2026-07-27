package homework_13.abstract_fabric_furniture;

public class ModernTable implements Table{
    @Override
    public void putOn() {
        System.out.println("Вы положили на современный стол");
    }
}
