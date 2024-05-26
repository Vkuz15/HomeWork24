package pro.sky.JavaSkyPro.service;

import pro.sky.JavaSkyPro.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    BigDecimal getMaxSalary(Integer department);
    BigDecimal getMinSalary(Integer department);
    BigDecimal getSumSalary(Integer department);
    List<Employee> getEmployeesDepartment(Integer department);
    Map<Integer, List<Employee>> getGroupedEmployeesByDepartment();

}
