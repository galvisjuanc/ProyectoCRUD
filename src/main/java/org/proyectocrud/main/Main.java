package org.proyectocrud.main;

import org.proyectocrud.model.Employee;
import org.proyectocrud.repository.EmployeeRepository;
import org.proyectocrud.repository.Repository;
import org.proyectocrud.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("---Listando todos---");
        Repository<Employee> repository = new EmployeeRepository();
        repository.findAll().forEach(System.out::println);

        System.out.println("---Buscando por ID---");
        System.out.println(repository.getById(2));

    }
}
