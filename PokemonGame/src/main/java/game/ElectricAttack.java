package game;

/**
 * Ataque de tipo Eléctrico.
 * Es fuerte contra Agua, neutral ante Fuego/Planta (por simplicidad).
 */
public class ElectricAttack extends Attack {
    public ElectricAttack(String name, int baseDamage, double accuracy) {
        super(name, baseDamage, accuracy);
    }

    @Override
    public int calculateDamage(Pokemon target) {
        String t = target.getType();
        if (t.equalsIgnoreCase("Agua")) {
            return (int)(baseDamage * 1.4); // ventaja moderada
        }
        return baseDamage;
    }
}
