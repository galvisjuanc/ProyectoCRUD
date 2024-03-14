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

            System.out.println(repository.getById(3));

            System.out.println("-- Insertando un empleado --");
            Employee employee = new Employee();
            employee.setFirst_name("Camilo");
            employee.setPa_surname("Pimentel");
            employee.setMa_surname("Gutierritos");
            employee.setEmail("camilopimentel@gmail.com");
            employee.setSalary(19000F);

            repository.save(employee);

            System.out.println("--- NUevo empleado insertado ---");
            repository.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La conexion a la base de datos falló");
        }
    }
}
