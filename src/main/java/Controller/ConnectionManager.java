package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final class SingletonHolder {
        public static final ConnectionManager INSTANCE = new ConnectionManager();
    }
    private Connection connection;

    public ConnectionManager() {
        String url = "jdbc:mysql://localhost:3306/phpmyadmin?serverTimezone=UTC";

        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static ConnectionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean close() throws SQLException {
        if(connection != null) {
            connection.close();
            return true;
        }
        return false;
    }

}

