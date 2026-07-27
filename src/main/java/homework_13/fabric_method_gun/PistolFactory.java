package homework_13.fabric_method_gun;

public class PistolFactory extends WeaponFactory{
    @Override
    public Weapon createWeapon() {
        return new Pistol();
    }
}
