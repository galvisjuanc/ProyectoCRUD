package org.proyectocrud.main;

import org.proyectocrud.model.Employee;
import org.proyectocrud.repository.EmployeeRepository;
import org.proyectocrud.repository.Repository;
import org.proyectocrud.util.DatabaseConnection;
import org.proyectocrud.view.SwingApp;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        SwingApp swingApp = new SwingApp();
        swingApp.setVisible(true);

        /* try (Connection connection = DatabaseConnection.getInstance()) {
            Repository<Employee> repository = new EmployeeRepository();
            repository.findAll().forEach(System.out::println);

            System.out.println(repository.getById(3));

            System.out.println("-- Insertando un empleado --");
            Employee employee = new Employee();
            employee.setFirst_name("Camila");
            employee.setPa_surname("Riano");
            employee.setMa_surname("Zarate");
            employee.setEmail("camilariano@gmail.com");
            employee.setSalary(24000F);

            repository.save(employee);

            System.out.println("--- Nuevo empleado insertado ---");
            repository.findAll().forEach(System.out::println);

            System.out.println("--- Actualizando o Insertando empleado --");
            Integer idEmployee = 8;
            Employee employeeUpdated = new Employee();
            employeeUpdated.setFirst_name("JuanCamilo");
            employeeUpdated.setPa_surname("Galvis");
            employeeUpdated.setMa_surname("Cuellar");
            employeeUpdated.setEmail("juancagalvis@gmail.com");
            employeeUpdated.setSalary(16000F);

            repository.update(idEmployee, employeeUpdated);
            System.out.println("Empleado actualizado");
            System.out.println(repository.getById(idEmployee));

            System.out.println("Empleado a eliminar con id: 9");
            repository.delete(9);

            System.out.println("------ Lista de Usuarios ------");
            repository.findAll().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La conexion a la base de datos falló");
        }*/
    }
}
