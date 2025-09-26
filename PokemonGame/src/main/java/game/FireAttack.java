package game;

/**
 * Ataque de tipo Fuego.
 * Es fuerte contra Planta, débil contra Agua.
 */
public class FireAttack extends Attack {
    public FireAttack(String name, int baseDamage, double accuracy) {
        super(name, baseDamage, accuracy);
    }

    @Override
    public int calculateDamage(Pokemon target) {
        String t = target.getType();
        if (t.equalsIgnoreCase("Planta")) {
            return (int)(baseDamage * 1.5); // ventaja
        } else if (t.equalsIgnoreCase("Agua")) {
            return (int)(baseDamage * 0.7); // desventaja
        }
        return baseDamage;
    }
}
