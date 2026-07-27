package homework_13.abstract_fabric_furniture;

public class ClassicChair implements Chair{
    @Override
    public void sitOn() {
        System.out.println("Вы сели на классический стул");
    }
}
