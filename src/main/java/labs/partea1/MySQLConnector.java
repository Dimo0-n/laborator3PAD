package labs.partea1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static Connection connection;

    public static void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {

            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            if (url == null || url.isBlank()) {
                // Permit fallback build from individual parts only if provided as env vars
                String host = System.getenv("DB_HOST");
                String port = System.getenv("DB_PORT");
                String db = System.getenv("DB_NAME");

                if (host != null && port != null && db != null) {
                    url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";
                }
            }

            if (url == null || url.isBlank()) {
                throw new IllegalStateException("Missing DB_URL (or DB_HOST/DB_PORT/DB_NAME) environment variables");
            }

            if (user == null || user.isBlank()) {
                throw new IllegalStateException("Missing DB_USER environment variable");
            }

            if (pass == null || pass.isBlank()) {
                throw new IllegalStateException("Missing DB_PASS environment variable");
            }

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
