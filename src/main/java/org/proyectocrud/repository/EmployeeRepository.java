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
    public void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?, ?, ?, ?, ?)";
        insertOrUpdateEmployee(employee, sql);
    }

    @Override
    public void update(Integer id, Employee employee) throws SQLException {
        if (getById(id) != null) {
            String sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ? WHERE id = " + id;
            insertOrUpdateEmployee(employee, sql);
            System.out.println("Empleado existe, actualización realizada");
        } else {
            save(employee);
            System.out.println("Empleado no existe, nuevo usuario creado");
        }
    }

    private void insertOrUpdateEmployee(Employee employee, String sql) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getPa_surname());
            preparedStatement.setString(3, employee.getMa_surname());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setFloat(5, employee.getSalary());

            preparedStatement.executeUpdate();
        }
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
