package game;

import java.util.List;

/**
 * Clase base para los Pokémon. Mantiene nombre, tipo, vida y lista de ataques.
 */
public abstract class Pokemon {
    private final String name;
    private final String type; // "Fuego", "Agua", "Planta", "Eléctrico"
    private int hp;
    private final List<Attack> attacks;

    public Pokemon(String name, String type, int hp, List<Attack> attacks) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attacks = attacks;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getHp() { return hp; }
    public List<Attack> getAttacks() { return attacks; }

    // Reduce HP sin permitir valores negativos
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public boolean isFainted() {
        return hp <= 0;
    }
}
