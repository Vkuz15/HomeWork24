package pro.sky.JavaSkyPro.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.JavaSkyPro.model.Employee;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;

    Collection<Employee> employees = List.of(
            new Employee("Игорь", "Рыбаков", 1, new BigDecimal(5000)),
            new Employee("Дмитрий", "Смайлов", 2, new BigDecimal(4000)),
            new Employee("Мария", "Цветаева", 1, new BigDecimal(1000)),
            new Employee("Сергей", "Щербаков", 2, new BigDecimal(3000)),
            new Employee("Анна", "Балибардина", 1, new BigDecimal(2000))
    );

    Map<Integer, List<Employee>> employeesByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartmentNumber));

    @BeforeEach
    void setUp() {
        Mockito.when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    void getMaxSalary1() {
        BigDecimal result = departmentService.getMaxSalary(1);
        Assertions.assertEquals(new BigDecimal(5000), result);
    }

    @Test
    void getMaxSalary2() {
        BigDecimal result = departmentService.getMaxSalary(5);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void getMinSalary() {
        BigDecimal result = departmentService.getMinSalary(2);
        Assertions.assertEquals(new BigDecimal(3000), result);
    }

    @Test
    void getSumSalary1() {
        BigDecimal result = departmentService.getSumSalary(1);
        Assertions.assertEquals(new BigDecimal(8000), result);
    }

    @Test
    void getSumSalary2() {
        BigDecimal result = departmentService.getSumSalary(null);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void getEmployeesDepartment() {
        List<Employee> employeeDepartment = departmentService.getEmployeesDepartment(1);
        Assertions.assertEquals(3, employeeDepartment.size());
        Assertions.assertTrue(employeesByDepartment.get(1).containsAll(employeeDepartment));
    }

    @Test
    void getGroupedEmployeesByDepartment() {
        Map<Integer, List<Employee>> groupedEmployeesByDepartment = departmentService.getGroupedEmployeesByDepartment();
        Assertions.assertEquals(groupedEmployeesByDepartment, employeesByDepartment);
    }
}