package homework_mock.task_oop.transport_7service;

import java.util.ArrayList;
import java.util.List;

/**
 * "Система работает с разными видами транспорта:
 *
 * автомобиль
 * велосипед
 * самолет
 *
 * У каждого транспорта есть название.
 *
 * Все виды транспорта умеют:
 *
 * двигаться
 *
 * Но каждый делает это по-своему:
 *
 * автомобиль едет по дороге
 * велосипед крутит педали
 * самолет летит по воздуху
 *
 * Нужно реализовать систему, которая может запустить движение всего транспорта.
 *
 * Требование:
 *
 * Метод, который запускает движение, должен работать со списком транспорта, не зная их конкретный тип.
 * Каждый тип транспорта должен сам реализовывать, как он двигается.
 *
 * Метод должен вывести, например:
 *
 * Машина Tesla едет по дороге
 * Велосипед BMX крутит педали
 * Самолет Boeing летит по воздуху"
 */

public class Main {
    public static void main(String[] args) {
        List<Transport> transports = new ArrayList<>();
        transports.add(new Car("Tesla"));
        transports.add(new Bicycle("BMX"));
        transports.add(new Airplane("Boeing"));

        TransportService transportService = new TransportService();
        transportService.startAll(transports);
    }
}
