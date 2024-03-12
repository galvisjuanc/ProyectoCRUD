package org.proyectocrud.main;

import org.proyectocrud.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnection.getInstance();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");){

            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name") + ", " + resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La conexion a la base de datos falló");
        }
    }
}
