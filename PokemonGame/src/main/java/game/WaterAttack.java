package game;

/**
 * Ataque de tipo Agua.
 * Es fuerte contra Fuego, débil contra Planta.
 */
public class WaterAttack extends Attack {
    public WaterAttack(String name, int baseDamage, double accuracy) {
        super(name, baseDamage, accuracy);
    }

    @Override
    public int calculateDamage(Pokemon target) {
        String t = target.getType();
        if (t.equalsIgnoreCase("Fuego")) {
            return (int)(baseDamage * 1.5);
        } else if (t.equalsIgnoreCase("Planta")) {
            return (int)(baseDamage * 0.7);
        }
        return baseDamage;
    }
}
