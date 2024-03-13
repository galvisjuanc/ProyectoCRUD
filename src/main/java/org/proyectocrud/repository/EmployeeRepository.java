package org.proyectocrud.repository;

import org.proyectocrud.model.Employee;
import org.proyectocrud.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {
            while(resultSet.next()) {
                Employee e = createEmployee(resultSet);
                employees.add(e);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    employee = createEmployee(resultSet);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirst_name(resultSet.getString("first_name"));
        employee.setPa_surname(resultSet.getString("pa_surname"));
        employee.setMa_surname(resultSet.getString("ma_surname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setSalary(resultSet.getFloat("salary"));
        return employee;
    }
}
