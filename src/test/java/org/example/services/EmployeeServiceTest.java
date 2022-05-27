package org.example.services;

import org.example.dao.EmployeeRepository;
import org.example.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employee_repository;

    @InjectMocks
    EmployeeService employee_service;

    Employee test_employee1;
    Employee test_employee2;

    @BeforeEach
    public void setup(){
        test_employee1 = new Employee(1,1, "tom", 1);
        test_employee2 = new Employee(2, 1, "mat", 2);
    }

    @Test
    void add_employee_test() {
        Employee savedEmployee1 = employee_service.add_employee(test_employee1);

        assertThat(savedEmployee1).isNotNull();
    }

    @Test
    void get_all_employees_test() {
        employee_service.add_employee(test_employee1);
        employee_service.add_employee(test_employee2);

        given(employee_repository.findAll()).willReturn(List.of(test_employee1,test_employee2));

        List<Employee> employee_list = employee_service.get_all_employees();

        assertThat(employee_list).isNotNull();
        assertThat(employee_list.size()).isEqualTo(2);
    }

    @Test
    void get_employee_by_id_test() {
        employee_service.add_employee(test_employee1);

        given(employee_repository.getById(test_employee1.getEmployee_id())).willReturn(test_employee1);

        assertThat(employee_service.get_employee_by_id(test_employee1.getEmployee_id())).isEqualTo(test_employee1);
    }

    @Test
    void delete_employee_test() {
        employee_service.add_employee(test_employee1);

        employee_service.delete_employee(test_employee1.getEmployee_id());

        Employee deleted_employee = employee_service.get_employee_by_id(test_employee1.getEmployee_id());
        assertThat(deleted_employee).isNull();
    }

    @Test
    void update_employee() {
        employee_service.add_employee(test_employee1);
        
        given(employee_repository.getById(1L)).willReturn(test_employee1);
        
        Employee new_employee = new Employee(1,1, "dave", 1000);
        employee_service.update_employee(new_employee,1L);

        assertThat(employee_service.get_employee_by_id(1L)).isEqualTo(new_employee);
    }
}