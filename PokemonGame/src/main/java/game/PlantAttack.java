package game;

/**
 * Ataque de tipo Planta.
 * Es fuerte contra Agua, débil contra Fuego.
 */
public class PlantAttack extends Attack {
    public PlantAttack(String name, int baseDamage, double accuracy) {
        super(name, baseDamage, accuracy);
    }

    @Override
    public int calculateDamage(Pokemon target) {
        String t = target.getType();
        if (t.equalsIgnoreCase("Agua")) {
            return (int)(baseDamage * 1.5);
        } else if (t.equalsIgnoreCase("Fuego")) {
            return (int)(baseDamage * 0.7);
        }
        return baseDamage;
    }
}
