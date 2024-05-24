package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    static Connection connection = null;

    public static Connection makeConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/EduHub";
            String dbUsername = "postgres";
            String dbPassword = "1234";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
