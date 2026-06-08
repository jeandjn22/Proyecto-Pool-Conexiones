package pool;

import model.Connection;
import model.PgConnection;
public class PoolManager {
    private final Pool pool;
    private final int minSize;
    private final int maxSize;

    public PoolManager(int minSize, int maxSize) {
        this.pool = Pool.getInstance();
        this.minSize = minSize;
        this.maxSize = maxSize;

        initPool();
    }

    private void initPool() {
        for (int i = 0; i < minSize; i++) {
            pool.addConnection(new PgConnection());
        }
        IO.print("Pool inicializado con " + minSize + " conexiones.");
    }

    public Connection getConn() {
        Connection conn = pool.getConn();

        if (conn == null) {
            synchronized (this) {
                IO.print("No hay conexiones libres, incrementando cantidad");
                grow();
                conn = pool.getConn();
            }
        }
        return conn;
    }

    public void releaseConn(Connection conn) {
        pool.releaseConn(conn);

        if (pool.getAvailableConnections() > minSize) {
            shrink();
        }
    }

    public void grow() {
        pool.addConnection(new PgConnection());
    }

    public synchronized void shrink() {
        Connection connToRemove = getConn();

        while (pool.getAvailableConnections() > minSize) {
            if (connToRemove != null) {
                IO.print("Eliminando conexion para ahorrar memoria");
                connToRemove.close();
            } else {
                break;
            }
        }
    }
}
