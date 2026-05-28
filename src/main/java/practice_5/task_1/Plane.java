package practice_5.task_1;

public class Plane extends Transport {
    public Plane() {
        super(900, 100000);
    }

    @Override
    void start() {
        System.out.println("САМОЛЕТ полетел!");
    }
}
