package practice_5.task_1;

public class Main {
    public static void main(String[] args) {
        Transport car = new Car();
        Transport sheep = new Ship();
        Transport plane = new Plane();

        Dispatcher dispatcher = new Dispatcher();

        dispatcher.control(car);
        dispatcher.printTransportDetails(car);

        dispatcher.control(sheep);
        dispatcher.printTransportDetails(sheep);

        dispatcher.control(plane);
        dispatcher.printTransportDetails(plane);
    }
}
