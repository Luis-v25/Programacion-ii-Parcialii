package game;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controla el flujo del combate: turnos, ejecución de ataques,
 * aplicación de reglas de daño (DamageRule) y resumen final.
 */
public class BattleManager {
    private final Pokemon player;
    private final Pokemon cpu;
    private final List<String> log = new ArrayList<>();
    private final List<Integer> damageHistory = new ArrayList<>();
    private final Random rng = new Random();

    public BattleManager(Pokemon player, Pokemon cpu) {
        this.player = player;
        this.cpu = cpu;
    }

    /**
     * Inicia la batalla. scanner y playerName vienen desde Main.
     */
    public void start(Scanner scanner, String playerName) {
        System.out.println("\n--- COMBATE INICIADO ---");
        while (!player.isFainted() && !cpu.isFainted()) {
            showStatus();
            playerTurn(scanner, playerName);
            if (cpu.isFainted()) break;
            cpuTurn();
        }
        showResult(playerName);
    }

    private void showStatus() {
        System.out.println("\n" + player.getName() + " HP: " + player.getHp() +
                "  |  " + cpu.getName() + " HP: " + cpu.getHp());
    }

    private void playerTurn(Scanner scanner, String playerName) {
        System.out.println("\nEs tu turno. Elige un ataque (escribe el número):");
        List<Attack> att = player.getAttacks();
        for (int i = 0; i < att.size(); i++) {
            Attack a = att.get(i);
            System.out.println((i + 1) + ") " + a.getName() + " (Daño base: " + a.getBaseDamage()
                    + ", Precisión: " + (int)(a.getAccuracy() * 100) + "%)");
        }

        String line = scanner.nextLine().trim();
        try {
            int chosenIndex = Integer.parseInt(line) - 1;
            if (chosenIndex < 0 || chosenIndex >= att.size())
                throw new InvalidChoiceException("Número de ataque fuera de rango.");
            Attack chosen = att.get(chosenIndex);

            // Ejemplo de uso de DamageRule como lambda: 10% probabilidad de crítico x2
            DamageRule rule = base -> {
                if (rng.nextDouble() < 0.10) { // 10% crítico
                    System.out.println("¡Golpe crítico!");
                    return base * 2;
                }
                return base;
            };

            executeAttack(player, cpu, chosen, playerName, rule);
        } catch (NumberFormatException nfe) {
            System.out.println("Debes ingresar un número.");
        } catch (InvalidChoiceException ice) {
            System.out.println("Opción inválida: " + ice.getMessage());
        } catch (AttackMissedException ame) {
            // executeAttack lanza esta excepción si falla; solo mostramos mensaje (ya registrado)
            System.out.println(ame.getMessage());
        }
    }

    private void cpuTurn() {
        List<Attack> att = cpu.getAttacks();
        Attack chosen = att.get(rng.nextInt(att.size()));

        // CPU usa regla simple (sin crítico)
        DamageRule ruleCpu = base -> base;
        try {
            executeAttack(cpu, player, chosen, "CPU", ruleCpu);
        } catch (AttackMissedException ame) {
            System.out.println("CPU: " + ame.getMessage());
        }
    }

    /**
     * Ejecuta un ataque considerando precisión, polimorfismo y regla de daño.
     * Lanza AttackMissedException si el ataque falla por precisión.
     */
    private void executeAttack(Pokemon attacker, Pokemon target, Attack attack,
                               String attackerLabel, DamageRule rule) throws AttackMissedException {
        System.out.println("\n" + attackerLabel + " intenta usar " + attack.getName() + "...");
        if (!attack.hits()) {
            String fail = attacker.getName() + " falló con " + attack.getName();
            log.add(fail);
            throw new AttackMissedException("¡El ataque falló!");
        }

        int dmg = attack.calculateDamage(target);
        int finalDmg = rule.apply(dmg); // aplica la lambda (ej.: crítico)
        target.takeDamage(finalDmg);

        String event = attacker.getName() + " usó " + attack.getName() +
                " y causó " + finalDmg + " de daño (tipo: " + attack.getClass().getSimpleName() + ")";
        log.add(event);
        damageHistory.add(finalDmg);

        System.out.println("Resultado: " + event);
    }

    private void showResult(String playerName) {
        System.out.println("\n--- FIN DEL COMBATE ---");
        String winner;
        if (player.isFainted() && cpu.isFainted()) winner = "Empate";
        else if (!player.isFainted()) winner = playerName;
        else winner = "CPU";

        System.out.println("Ganador: " + winner);

        System.out.println("\nRegistro de eventos:");
        log.forEach(System.out::println);

        // Estadísticas con Streams (top 3 y promedio)
        System.out.println("\n--- Estadísticas ---");
        long misses = log.stream().filter(s -> s.toLowerCase().contains("falló")).count();
        System.out.println("Ataques fallidos: " + misses);

        List<Integer> top = damageHistory.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top golpes (máximos): " + top);

        double avg = damageHistory.stream().mapToInt(i -> i).average().orElse(0);
        System.out.println("Daño promedio: " + String.format("%.2f", avg));
    }
}
