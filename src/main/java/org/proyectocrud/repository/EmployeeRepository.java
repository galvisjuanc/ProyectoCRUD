package org.proyectocrud.repository;

import org.proyectocrud.model.Employee;
import org.proyectocrud.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement();
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
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
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
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?, ?, ?, ?, ?, ?)";
        insertOrUpdateEmployee(employee, sql);
    }

    @Override
    public void update(Integer id, Employee employee) throws SQLException {
        if (getById(id) != null) {
            String sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ?, curp= ? WHERE id = " + id;
            insertOrUpdateEmployee(employee, sql);
            System.out.println("Empleado existe, actualización realizada");
        } else {
            save(employee);
            System.out.println("Empleado no existe, nuevo usuario creado");
        }
    }

    private void insertOrUpdateEmployee(Employee employee, String sql) throws SQLException {
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getPa_surname());
            preparedStatement.setString(3, employee.getMa_surname());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setFloat(5, employee.getSalary());
            preparedStatement.setString(6, employee.getCurp());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirst_name(resultSet.getString("first_name"));
        employee.setPa_surname(resultSet.getString("pa_surname"));
        employee.setMa_surname(resultSet.getString("ma_surname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setSalary(resultSet.getFloat("salary"));
        employee.setCurp(resultSet.getString("curp"));
        return employee;
    }
}
