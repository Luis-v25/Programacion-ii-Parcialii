package game;

/**
 * Interfaz funcional para poder modificar el daño calculado.
 * Permite usar una lambda como regla (ej.: crítico).
 */
@FunctionalInterface
public interface DamageRule {
    int apply(int baseDamage);
}
