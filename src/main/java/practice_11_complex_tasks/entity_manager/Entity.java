package practice_11_complex_tasks.entity_manager;

public abstract class Entity {


    private final String name;
    private final int age;
    private final boolean isActive;

    public Entity(String someName, int someAge, boolean someIsActive) {

        this.name = someName;
        this.age = someAge;
        this.isActive = someIsActive;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }
}
