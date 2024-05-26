package pro.sky.JavaSkyPro.service;

import org.springframework.stereotype.Service;
import pro.sky.JavaSkyPro.exception.EmployeeException;
import pro.sky.JavaSkyPro.exception.EmployeeFullException;
import pro.sky.JavaSkyPro.exception.EmployeeNotFoundException;
import pro.sky.JavaSkyPro.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceMapImpl implements EmployeeService{
    private static final int constant = 5;
    public Map<String, Employee> employees = new HashMap<>(constant);

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= constant) {
            throw new EmployeeFullException("Превышен лимит сотрудников");
        }
        Employee employee = new Employee(firstName, lastName);

        if (employees.containsKey(firstName + lastName)) {
            throw new EmployeeException("Такой сотрудник уже есть");
        }
        employees.put(firstName + lastName, employee);
        return employee;
    }

    @Override
    public Employee delete(String firstName, String lastName) {
        Employee employee = employees.remove(firstName + lastName);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException("Данный сотрудник отсутствует в хранилище" + firstName + lastName);
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(firstName + lastName);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException("Данного сотрудника нет");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
