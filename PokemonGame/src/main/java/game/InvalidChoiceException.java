package game;

/**
 * Se lanza cuando el jugador elige una opción inválida (número fuera de rango).
 */
public class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) {
        super(message);
    }
}
