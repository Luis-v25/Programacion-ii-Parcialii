package game;

import java.util.Arrays;

/**
 * Pikachu: tipo Eléctrico
 */
public class Pikachu extends Pokemon {
    public Pikachu() {
        super("Pikachu", "Eléctrico", 95,
                Arrays.asList(
                        new ElectricAttack("Impactrueno", 18, 0.90),
                        new ElectricAttack("Rayo", 25, 0.80)
                ));
    }
}
