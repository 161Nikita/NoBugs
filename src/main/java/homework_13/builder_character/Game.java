package homework_13.builder_character;

public class Game {
    public Character constructPowerfulCharacter(String name) {
        return new Character.CharacterBuilder(name)
                .setHealth(120)
                .setArmor(13)
                .setDamage(16)
                .setMagic(0)
                .build();
    }

    public Character constructDefaultCharacter(String name) {
        return new Character.CharacterBuilder(name)
                .setHealth(100)
                .setArmor(5)
                .setDamage(10)
                .setMagic(0)
                .build();
    }
}