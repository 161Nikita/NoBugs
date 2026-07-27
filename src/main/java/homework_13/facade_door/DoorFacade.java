package homework_13.facade_door;

public class DoorFacade {
    private final DoorOpener opener;
    private final DoorCloser closer;
    private final DoorLocker locker;

    public DoorFacade() {
        this.opener = new DoorOpener();
        this.closer = new DoorCloser();
        this.locker = new DoorLocker();
    }

    // метод открытия двери
    public void openDoor() {
        System.out.println("[Фасад]: Выполняю команду открытия дверей");
        locker.unlock();
        opener.open();
    }
    public void closeDoor() {
        System.out.println("[Фасад]: Выполняю команду закрытия дверей с блокировкой");
        closer.close();
        locker.lock();
    }
}
