package homework_13.fabric_method_gun;

public class Main {
    public static void main(String[] args) {

        WeaponFactory swordFactory = new SwordFactory();
        swordFactory.useWeapon();
        WeaponFactory bowFactory = new BowFactory();
        bowFactory.useWeapon();
        WeaponFactory pistolFactory = new PistolFactory();
        pistolFactory.useWeapon();
    }
}
