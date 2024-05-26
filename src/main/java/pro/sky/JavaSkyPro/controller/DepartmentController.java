package pro.sky.JavaSkyPro.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.JavaSkyPro.model.Employee;
import pro.sky.JavaSkyPro.service.DepartmentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> employeesDepartment(@PathVariable Integer department) {
        return departmentService.getEmployeesDepartment(department);
    }

    @GetMapping("/{id}/salary/sum")
    public BigDecimal sumSalary(@PathVariable Integer department) {
        return departmentService.getSumSalary(department);
    }

    @GetMapping("/{id}/salary/max")
    public BigDecimal maxSalary(@PathVariable Integer department) {
        return departmentService.getMaxSalary(department);
    }

    @GetMapping("/{id}/salary/min")
    public BigDecimal minSalary(@PathVariable Integer department) {
        return departmentService.getMinSalary(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> allEmployees() {
        return departmentService.getGroupedEmployeesByDepartment();
    }
}
