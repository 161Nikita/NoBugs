package homework_11.entity_manager;

public abstract class Entity {

    private final String name;
    private final int age;
    private final boolean isActive;

    public Entity(String name, int age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }
}
