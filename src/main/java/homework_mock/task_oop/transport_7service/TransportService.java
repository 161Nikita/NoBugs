package homework_mock.task_oop.transport_7service;

import java.util.List;

public class TransportService {
    public void startAll(List<Transport> transports) {
        for (Transport transport : transports) {
            transport.move();
        }
    }
}
