package homework_4.zoo;

public abstract class Animal {

    private String sound;
    private String movement;
    private String type;

    public Animal(String type, String sound, String movement) {
        this.sound = sound;
        this.movement = movement;
        this.type = type;
    }

    public String getSound() {
        return sound;
    }

    public String getMovement() {
        return movement;
    }

    public String getType() {
        return type;
    }

    abstract void demonstration();
}