package labs.partea1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static Connection connection;

    public static void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {

            String host = System.getenv().getOrDefault("DB_HOST", "localhost");
            String port = System.getenv().getOrDefault("DB_PORT", "3306");
            String db   = System.getenv().getOrDefault("DB_NAME", "lab");

            String user = System.getenv().getOrDefault("DB_USER", "root");
            String pass = System.getenv().getOrDefault("DB_PASS", "root");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";

            connection = DriverManager.getConnection(url, user, pass);
        }
    }

    public static Connection getConnection() throws SQLException {
        connect();
        return connection;
    }

    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
