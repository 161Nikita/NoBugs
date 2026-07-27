package homework_13.fabric_method_gun;

public class SwordFactory extends WeaponFactory{
    @Override
    public Weapon createWeapon() {
        return new Sword();
    }
}
