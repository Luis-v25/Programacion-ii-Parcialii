package game;

import java.util.Random;

/**
 * Clase abstracta base para ataques.
 * Implementa la precisión y el método hits().
 * Cada subtipo define su propio calculateDamage(Pokemon).
 */
public abstract class Attack {
    protected final String name;
    protected final int baseDamage;
    protected final double accuracy; // 0.0 - 1.0
    private static final Random RAND = new Random();

    public Attack(String name, int baseDamage, double accuracy) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
    }

    public String getName() { return name; }
    public int getBaseDamage() { return baseDamage; }
    public double getAccuracy() { return accuracy; }

    // Decide si el ataque acierta según su precisión
    public boolean hits() {
        return RAND.nextDouble() < this.accuracy;
    }

    // Polimorfismo: cada subtipo calcula daño según el Pokémon objetivo
    public abstract int calculateDamage(Pokemon target);
}
