package org.example.services;

import org.example.dao.EmployeeRepository;
import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee add_employee(Employee employee){
        employeeRepository.save(employee);
        System.out.println("Employee: " + employee.toString());
        return employee;
    }

    public List<Employee> get_all_employees(){
        return employeeRepository.findAll();
    }

    public Employee get_employee_by_id(Long id){
        return employeeRepository.getById(id);
    }

    public Employee update_employee(Employee employee, Long id){
        Employee employeeDB = employeeRepository.getById(id);
        employeeDB.setEmployee_name(employee.getEmployee_name());
        employeeDB.setManager_id(employee.getManager_id());
        employeeDB.setYears_of_experience(employee.getYears_of_experience());
        employeeRepository.save(employeeDB);
        return employeeDB;
    }

    public void delete_employee(Long id){
        employeeRepository.deleteById(id);
    }
}