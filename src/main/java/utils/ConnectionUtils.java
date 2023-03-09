package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost/inventory";
    private static final String UNAME = "root";
    private static final String PASSWORD = "root1234";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // this will be thread safe as we are currently not using multi threads
            if (connection != null) {
                return connection;
            }
            connection = DriverManager.getConnection(URL, UNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
