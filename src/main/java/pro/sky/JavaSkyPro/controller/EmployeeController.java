package pro.sky.JavaSkyPro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.JavaSkyPro.model.Employee;
import pro.sky.JavaSkyPro.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.add(firstName, lastName);
        return generateMessage(result, "Успешно создан");
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.delete(firstName, lastName);
        return generateMessage(result, "Удален");
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> getFindAll() {
        return employeeService.findAll();
    }

    private String generateMessage(Employee employee, String text) {
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(),
                employee.getLastName(),
                text
        );
    }
}
