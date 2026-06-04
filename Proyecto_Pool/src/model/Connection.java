package model;

public abstract class Connection {
    protected int retries;
    protected int timeout;

    // Constructor base para que herede cualquier base de datos
    public Connection(int retries, int timeout) {
        this.retries = retries;
        this.timeout = timeout;
    }

    // El método polimórfico que usarán tus compañeros en los hilos
    public abstract void query();

    // Getters por si el Pool o el Simulador necesitan consultar las propiedades
    public int getRetries() { return retries; }
    public int getTimeout() { return timeout; }
}