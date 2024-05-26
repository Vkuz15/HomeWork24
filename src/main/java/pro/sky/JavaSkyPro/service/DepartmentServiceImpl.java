package pro.sky.JavaSkyPro.service;

import org.springframework.stereotype.Service;
import pro.sky.JavaSkyPro.model.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public BigDecimal getMaxSalary(Integer department) {
        BigDecimal max = BigDecimal.ZERO;
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentNumber().equals(department) && employee.getSalary().compareTo(max) > 0) {
                max = employee.getSalary();
            }
        }
        return max;
    }

    @Override
    public BigDecimal getMinSalary(Integer department) {
        BigDecimal min = new BigDecimal(Double.MAX_VALUE);
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentNumber().equals(department) && employee.getSalary().compareTo(min) < 0) {
                min = employee.getSalary();
            }
        }
        return min;
    }

    public BigDecimal getSumSalary(Integer department) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentNumber().equals(department)) {
                sum = sum.add(employee.getSalary());
            }
        }
        return sum;
    }

    @Override
    public List<Employee> getEmployeesDepartment(Integer department) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentNumber().equals(department)) {
                list.add(employee);
            }
        }
        return list;
    }

    @Override
    public Map<Integer, List<Employee>> getGroupedEmployeesByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentNumber));
    }
}
