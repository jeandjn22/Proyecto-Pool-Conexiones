package model;

import java.util.Random;

public class PgConnection implements Connection {
    private static final Random random = new Random();
    private final int retries;
    private final int timeout;
    private boolean active;

    public PgConnection(int retries, int timeout) {
        this.retries = retries;
        this.timeout = timeout;
        this.active = false;
    }

    @Override
    public void connect() {
        this.active = true;
        System.out.println("[PostgreSQL] Conexión física establecida. Configuración -> Reintentos: " + retries + ", Timeout: " + timeout + "ms.");
    }

    @Override
    public void query(String sql) {
        if (!active) {
            System.err.println("[PostgreSQL] Error crítico: Intentando consultar en una conexión cerrada o destruida.");
            return;
        }
        try {
            int simulatedDelay = 100 + random.nextInt(400);
            Thread.sleep(simulatedDelay); 
            System.out.println("[PostgreSQL] Ejecutando: \"" + sql + "\" (" + simulatedDelay + "ms).");
        } catch (InterruptedException e) {
            System.err.println("[PostgreSQL] Consulta interrumpida de forma abrupta.");
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        this.active = false;
        System.out.println("[PostgreSQL] Conexión liberada y socket cerrado correctamente.");
    }

    public int getRetries() {
        return retries;
    }

    public int getTimeout() {
        return timeout;
    }
}