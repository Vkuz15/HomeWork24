package pro.sky.JavaSkyPro.service;

import java.util.Collection;
import pro.sky.JavaSkyPro.model.Employee;

public interface EmployeeService {

    Employee add(String firstName, String lastName);
    Employee delete(String firstName, String lastName);
    Employee find(String firstName, String lastName);
    Collection<Employee> findAll();
}
