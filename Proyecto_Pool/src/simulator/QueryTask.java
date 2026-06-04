package simulator;

import model.Connection;

public class QueryTask implements Runnable {
    private final String clientName;
    private final Connection connection;

    public QueryTask(String clientName, Connection connection) {
        this.clientName = clientName;
        this.connection = connection;
    }

    @Override
    public void run() {
        System.out.println("[" + clientName + "] Iniciando petición...");
        
        if (connection != null) {
            // Se ejecuta el método polimórfico que definiste
            connection.query();
            System.out.println("[" + clientName + "] Petición finalizada con éxito.");
        } else {
            System.err.println("[" + clientName + "] Error: No se pudo obtener una conexión.");
        }
    }
}
