package homework_13.builder_character;

public class Character {

    private final String name;
    private final int health;
    private final int damage;
    private final int armor;
    private final int magic;

    private Character(CharacterBuilder builder) {
        this.name = builder.name;
        this.health = builder.health;
        this.damage = builder.damage;
        this.armor = builder.armor;
        this.magic = builder.magic;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getMagic() {
        return magic;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", armor=" + armor +
                ", magic=" + magic +
                '}';
    }

    public static class CharacterBuilder {
        private String name;
        private int health = 100;
        private int damage = 10;
        private int armor = 5;
        private int magic = 0;

        public CharacterBuilder(String name) {
            this.name = name;
        }

        public CharacterBuilder setHealth(int health) {
            this.health = health;
            return this;
        }

        public CharacterBuilder setDamage(int damage) {
            this.damage = damage;
            return this;
        }

        public CharacterBuilder setArmor(int armor) {
            this.armor = armor;
            return this;
        }

        public CharacterBuilder setMagic(int magic) {
            this.magic = magic;
            return this;
        }

        public Character build() {
            return new Character(this);
        }
    }
}
