package game;

/**
 * Se lanza cuando un ataque falla por precisión.
 */
public class AttackMissedException extends Exception {
    public AttackMissedException(String message) {
        super(message);
    }
}
