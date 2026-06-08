package model;

public interface Connection {
    void connect();
    void query(String sql);
    void close();
}