package game;

import java.util.Arrays;

/**
 * Charmander: tipo Fuego
 */
public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", "Fuego", 100,
                Arrays.asList(
                        new FireAttack("Arañazo", 12, 0.90),
                        new FireAttack("Llamarada", 25, 0.75)
                ));
    }
}
