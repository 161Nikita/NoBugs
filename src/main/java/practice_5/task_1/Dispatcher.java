package practice_5.task_1;

public class Dispatcher {
    public void control(Transport transport) {
        transport.start();

    }

    public void printTransportDetails(Transport transport) {
        System.out.println("Скорость транспорта: " + transport.getSpeed());
        System.out.println("Вместимость транспорта: " + transport.getCapacity());
    }
}
