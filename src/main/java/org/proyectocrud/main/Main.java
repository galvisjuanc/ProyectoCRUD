package org.proyectocrud.main;

import org.proyectocrud.model.Employee;
import org.proyectocrud.repository.EmployeeRepository;
import org.proyectocrud.repository.Repository;
import org.proyectocrud.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnection.getInstance()) {

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            try {
                Repository<Employee> repository = new EmployeeRepository(connection);

                System.out.println("-- Insertado un nuevo cliente --");

                Employee e = new Employee();

                e.setFirst_name("Carlos");
                e.setPa_surname("Lopez");
                e.setMa_surname("Villa");
                e.setEmail("carl222@example.com");
                e.setSalary(300F);
                e.setCurp("MECjkl987guihio214");
                repository.save(e);
                connection.commit();

                e.setFirst_name("David");
                e.setPa_surname("Lopez");
                e.setMa_surname("Villa");
                e.setEmail("davlop@example.com");
                e.setSalary(300F);
                e.setCurp("MECjkl987guihio215");
                repository.save(e);
                connection.commit();

            } catch (SQLException sqlError) {
                connection.rollback();
                throw new RuntimeException(sqlError);
            }
        }
    }
}
