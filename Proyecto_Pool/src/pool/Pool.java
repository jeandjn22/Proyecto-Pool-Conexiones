package pool;

import model.Connection;
import model.PgConnection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pool {
    private static Pool instance;
    private final BlockingQueue<Connection> availableConnections;
    private int maxConnections;

    private Pool() {
        this.availableConnections = new LinkedBlockingQueue<>();
    }

    public static synchronized Pool getInstance() {
        if (instance == null) {
            instance = new Pool();
        }
        return instance;
    }

    public Connection getConn() {
        return availableConnections.poll();
    }

    public void releaseConn(Connection conn) {
        if(conn != null) {
            availableConnections.offer(conn);
        }
    }

    protected void addConnection(Connection conn) {
        if(conn != null) {
            availableConnections.offer(conn);
        }
    }

    public int getAvailableConnections() {
        return availableConnections.size();
    }
}
