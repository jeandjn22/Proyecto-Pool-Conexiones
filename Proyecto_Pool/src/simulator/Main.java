package simulator;

import model.Connection;
import model.PgConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SIMULADOR DE CONEXIONES ===");

        // 1. Instanciamos una conexión de prueba (PostgreSQL)
        // Pasamos por ejemplo: 3 intentos (retries) y 3000ms de timeout
        Connection pgConn1 = new PgConnection(3, 3000);
        Connection pgConn2 = new PgConnection(3, 3000);

        // 2. Creamos las tareas que ejecutarán los hilos simulando clientes concurrentes
        Runnable cliente1 = new QueryTask("Cliente-Alfa", pgConn1);
        Runnable cliente2 = new QueryTask("Cliente-Bravo", pgConn2);
        Runnable cliente3 = new QueryTask("Cliente-Charlie", pgConn1); // Comparte la conn1 para ver concurrencia

        // 3. Creamos y lanzamos los hilos (Threads)
        Thread hilo1 = new Thread(cliente1);
        Thread hilo2 = new Thread(cliente2);
        Thread hilo3 = new Thread(cliente3);

        // Arrancamos la ejecución concurrente
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperamos a que los hilos terminen antes de cerrar el programa principal
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.err.println("La simulación principal fue interrumpida.");
            Thread.currentThread().interrupt();
        }

        System.out.println("=== SIMULACION FINALIZADA ===");
    }
}