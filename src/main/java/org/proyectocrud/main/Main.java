package org.proyectocrud.main;

import org.proyectocrud.model.Employee;
import org.proyectocrud.repository.EmployeeRepository;
import org.proyectocrud.repository.Repository;
import org.proyectocrud.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnection.getInstance()) {
            Repository<Employee> repository = new EmployeeRepository();
            repository.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La conexion a la base de datos falló");
        }
    }
}
