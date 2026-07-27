package homework_13.facade_smart_home;

public class SmartHomeFacade {
    private final Light light;
    private final Conditioner conditioner;
    private final SecuritySystem securitySysteme;

    public SmartHomeFacade() {
        this.light = new Light();
        this.conditioner = new Conditioner();
        this.securitySysteme = new SecuritySystem();
    }

    public void iHome() {
        System.out.println("Сценарий: Я ДОМА");
        securitySysteme.turnOff();
        light.turnOn();
        conditioner.turnOn();
    }

    public void iLeaveHome() {
        System.out.println("Сценарий: Я УШЕЛ ИЗ ДОМА");
        conditioner.turnOff();
        light.turnOff();
        securitySysteme.turnOn();
    }
}
