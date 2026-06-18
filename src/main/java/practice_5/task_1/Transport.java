package practice_5.task_1;

public abstract class Transport {

    private double speed;
    private int capacity;

    public Transport(double speed, int capacity) {
        this.speed = speed;
        this.capacity = capacity;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getCapacity() {
        return this.capacity;
    }

    abstract void start();
}
