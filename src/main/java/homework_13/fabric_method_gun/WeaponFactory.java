package homework_13.fabric_method_gun;

public abstract class WeaponFactory {

    public abstract Weapon createWeapon();

    public void useWeapon() {
        createWeapon().attack();
    }

}
