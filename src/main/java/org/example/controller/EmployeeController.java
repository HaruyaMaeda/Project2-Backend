package org.example.controller;
import org.example.entity.Employee;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Employee add_employee(@RequestBody Employee employee){
        return employeeService.add_employee(employee);
    }

    @GetMapping
    public List<Employee> get_all_employees(){
        return employeeService.get_all_employees();
    }

    @GetMapping("/{id}")
    public Employee get_employee_by_id(@PathVariable("id") long id){
        return employeeService.get_employee_by_id(id);
    }

    @PutMapping("/{id}")
    public Employee update_employee(@RequestBody Employee employee, @PathVariable("id") long id){
        return employeeService.update_employee(employee, id);
    }

    @DeleteMapping("/{id_to_delete}")
    public void delete_employee(@PathVariable("id_to_delete") Long id){
        employeeService.delete_employee(id);
    }

}
