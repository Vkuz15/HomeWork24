package pro.sky.JavaSkyPro.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.JavaSkyPro.exception.EmployeeException;
import pro.sky.JavaSkyPro.exception.EmployeeFullException;
import pro.sky.JavaSkyPro.model.Employee;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceListImplTest {

    EmployeeService employeeService = new EmployeeServiceListImpl();

    @Test
    void add() {
        Employee expectedEmployee = new Employee("Роман", "Бабкин");

        Employee added = employeeService.add("Роман", "Бабкин");

        assertEquals(1, employeeService.findAll().size());
        assertEquals(expectedEmployee, added);

        Employee founded = employeeService.find("Роман", "Бабкин");

        assertEquals(expectedEmployee, founded);
    }

    @Test
    void shouldFindEmployeeTest() {
        Employee findEmployee = employeeService.add("Роман", "Бабкин");
        assertEquals(findEmployee, employeeService.find("Роман", "Бабкин"));
    }

    @Test
    void addException1() {
        employeeService.add("Роман", "Бабкин");

        Assertions.assertThrows(EmployeeException.class, () -> employeeService.add("Роман", "Бабкин"));
    }

    @Test
    void addException2() {
        employeeService.add("Роман", "Бабкин");
        employeeService.add("Антон", "Иванов");
        employeeService.add("Дмитрий", "Петров");
        employeeService.add("Илья", "Сазонов");

        Assertions.assertThrows(EmployeeFullException.class, () -> employeeService.add("Ахилес", "Бабуяшвинов"));
    }

    @Test
    void delete() {
        Employee expected = employeeService.add("Роман", "Бабкин");

        assertEquals(1, employeeService.findAll().size());
        assertTrue(employeeService.findAll().contains(expected));

        Employee actual = employeeService.delete("Роман", "Бабкин");
        assertEquals(expected, actual);
        assertTrue(employeeService.findAll().isEmpty());
    }

    @Test
    void findAll() {
        Employee employee1 = employeeService.add("Роман", "Бабкин");
        Employee employee2 = employeeService.add("Игорь", "Овчинников");
        Collection<Employee> expected = List.of(employee1, employee2);

        Collection<Employee> actual = employeeService.findAll();
        assertIterableEquals(expected, actual);
    }
}