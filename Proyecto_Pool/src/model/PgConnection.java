package model;

import java.util.Random;

public class PgConnection extends Connection {
    private static final Random random = new Random();

    public PgConnection(int retries, int timeout) {
        super(retries, timeout);
    }

    @Override
    public void query() {
        try {
            // Simula el procesamiento de la BD tardando entre 100 y 500 milisegundos
            int simulatedDelay = 100 + random.nextInt(400);
            Thread.sleep(simulatedDelay);
            
            System.out.println("[PostgreSQL] Query ejecutada con éxito (" + simulatedDelay + "ms).");
        } catch (InterruptedException e) {
            System.err.println("[PostgreSQL] Error en la conexión: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
        }
    }
}