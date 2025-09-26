package game;

import java.util.Arrays;

/**
 * Squirtle: tipo Agua
 */
public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", "Agua", 110,
                Arrays.asList(
                        new WaterAttack("Burbuja", 14, 0.92),
                        new WaterAttack("Hidrobomba", 26, 0.72)
                ));
    }
}
