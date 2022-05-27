package org.example.dao;

import org.example.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employee_repository;

    Employee test_employee1;
    Employee test_employee2;

    @BeforeEach
    public void setup(){
        test_employee1 = new Employee(1, "tom", 1);
        test_employee2 = new Employee(2, "mat", 2);
    }

    @Test
    public void injectedComponentsAreNotNull(){
        Employee savedEmployee = employee_repository.save(test_employee1);

        assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void findAllTest(){
        employee_repository.save(test_employee1);
        employee_repository.save(test_employee2);

        List<Employee> employeeList = employee_repository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void findByIdTest(){
        employee_repository.save(test_employee1);

        Employee employee_in_DB = employee_repository.findById(test_employee1.getEmployee_id()).get();

        assertThat(employee_in_DB).isNotNull();
    }

    @Test
    public void updateTest(){
        employee_repository.save(test_employee1);

        Employee saved_employee = employee_repository.findById(test_employee1.getEmployee_id()).get();
        saved_employee.setEmployee_name("new_name");

        Employee updated_employee = employee_repository.save(saved_employee);

        assertThat(updated_employee.getEmployee_name()).isEqualTo("new_name");

    }

    @Test
    public void deleteTest(){
        employee_repository.save(test_employee1);

        employee_repository.deleteById(test_employee1.getEmployee_id());

        Optional<Employee> deleted_employee = employee_repository.findById(test_employee1.getEmployee_id());
        assertThat(deleted_employee).isEmpty();
    }
}