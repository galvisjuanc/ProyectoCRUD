package org.proyectocrud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/project";
    private static String root = "root";
    private static String pw = "BrokenYouth_1711*";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, root, pw);
        }
        return connection;
    }
}
