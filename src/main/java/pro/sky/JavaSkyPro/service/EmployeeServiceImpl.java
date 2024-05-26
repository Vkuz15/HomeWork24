package pro.sky.JavaSkyPro.service;

import org.springframework.stereotype.Service;
import pro.sky.JavaSkyPro.exception.EmployeeException;
import pro.sky.JavaSkyPro.exception.EmployeeFullException;
import pro.sky.JavaSkyPro.exception.EmployeeNotFoundException;
import pro.sky.JavaSkyPro.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int constant = 5;
    public List<Employee> employees = new ArrayList<>(constant);

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= constant) {
            throw new EmployeeFullException("Превышен лимит сотрудников");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeException("Такой сотрудник уже есть");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee delete(String firstName, String lastName) {
        Employee employee2 = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employee.equals(employee2)) {
                employees.remove(employee);
            };
        }
        return null;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee3 = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employee.equals(employee3)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Данного сотрудника нет");
    }

    @Override
    public Collection<Employee> findAll() {
        return null;
    }
}
