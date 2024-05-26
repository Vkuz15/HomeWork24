package pro.sky.JavaSkyPro.service;

import pro.sky.JavaSkyPro.exception.EmployeeException;
import pro.sky.JavaSkyPro.exception.EmployeeFullException;
import pro.sky.JavaSkyPro.exception.EmployeeNotFoundException;
import pro.sky.JavaSkyPro.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static pro.sky.JavaSkyPro.utils.Constants.COUNT_EMPLOYEE;

public class EmployeeServiceListImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>(COUNT_EMPLOYEE);

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= COUNT_EMPLOYEE) {
            throw new EmployeeFullException("Список переполнен");
        }
        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeException("В списке уже есть этот employee.");
        }
        employees.add(employee);
        return employee;
    }


    @Override
    public Employee delete(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        boolean deleted = false;
        for(int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.equals(newEmployee)) {
                employees.remove(i);
            }
        }
        return newEmployee;
    }


    @Override
    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудника не смогли найти, Имя - " + firstName);
    }

    @Override
    public List<Employee> findAll() {
        return Collections.unmodifiableList(employees);
    }
}
