package homework_13.fabric_method_gun;

public class BowFactory extends WeaponFactory{
    @Override
    public Weapon createWeapon() {
        return new Bow();
    }
}
