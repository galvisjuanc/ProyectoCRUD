package org.proyectocrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "BrokenYouth_1711*");
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE employees " + "SET email= 'juan.galvis@gmail.com'" + "WHERE first_name = 'Juan'");
            System.out.println("Empleados actualzados: ");
            resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY pa_surname");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("first_name") + ", " + resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La conexion a la base de datos falló");
        }
    }
}
