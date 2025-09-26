package game;

import java.util.*;

/**
 * Punto de entrada. Crea los Pokémon, administra la selección y arranca BattleManager.
 * Coloca este archivo en src/game/MainGame.java (paquete game).
 */
public class MainGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Batalla Pokémon (consola) ===");
        System.out.print("Ingresa tu nombre: ");
        String playerName = scanner.nextLine().trim();
        if (playerName.isEmpty()) playerName = "Jugador";

        // Lista de Pokémon disponibles (mismo orden que en las imágenes)
        List<Pokemon> pokedex = Arrays.asList(
                new Pikachu(),
                new Charmander(),
                new Squirtle(),
                new Bulbasaur()
        );

        // Muestra opciones
        System.out.println("\nElige tu Pokémon inicial (escribe el número):");
        for (int i = 0; i < pokedex.size(); i++) {
            Pokemon p = pokedex.get(i);
            System.out.println((i + 1) + ") " + p.getName() + "  (Tipo: " + p.getType() + ", HP: " + p.getHp() + ")");
        }

        Pokemon playerPokemon = null;
        while (playerPokemon == null) {
            String line = scanner.nextLine().trim();
            try {
                int idx = Integer.parseInt(line) - 1;
                if (idx < 0 || idx >= pokedex.size()) {
                    System.out.println("Número fuera de rango. Intenta otra vez.");
                    continue;
                }
                playerPokemon = pokedex.get(idx);
            } catch (NumberFormatException nfe) {
                System.out.println("Debes ingresar un número.");
            }
        }

        // CPU elige distinto
        Pokemon cpuPokemon;
        Random r = new Random();
        do {
            cpuPokemon = pokedex.get(r.nextInt(pokedex.size()));
        } while (cpuPokemon.getName().equals(playerPokemon.getName()));

        System.out.println("\n" + playerName + " eligió a: " + playerPokemon.getName());
        System.out.println("La CPU eligió a: " + cpuPokemon.getName());

        // Inicia la batalla
        BattleManager manager = new BattleManager(playerPokemon, cpuPokemon);
        manager.start(scanner, playerName);

        System.out.println("\nGracias por jugar.");
    }
}
