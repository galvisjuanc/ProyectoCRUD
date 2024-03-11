package org.proyectocrud.main;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project";
        String root = "root";
        String pw = "BrokenYouth_1711*";

        try (Connection connection = DriverManager.getConnection(url,root,pw);
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
