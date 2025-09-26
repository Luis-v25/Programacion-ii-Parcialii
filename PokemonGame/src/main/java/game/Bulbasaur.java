package game;

import java.util.Arrays;

/**
 * Bulbasaur: tipo Planta
 */
public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", "Planta", 105,
                Arrays.asList(
                        new PlantAttack("Latigazo", 15, 0.90),
                        new PlantAttack("Rayo Solar", 27, 0.70)
                ));
    }
}
