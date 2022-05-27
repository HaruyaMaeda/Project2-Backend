package org.example.services;

import org.aspectj.lang.annotation.Before;
import org.example.dao.CustomerRepository;
import org.example.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DataJpaTest
class CustomerServiceTest {

    @Mock
    CustomerRepository customer_repository;

    @InjectMocks
    CustomerService customer_service;

    Customer test_customer1;
    Customer test_customer2;


    @BeforeEach
    public void setup(){
        test_customer1 = new Customer(1l, "tom_username", "tom_password", "tom", "mot", "tom@gmail.com", false);
        test_customer2 = new Customer(2l, "mat_username", "mat_password", "mat", "tam", "mat@gmail.com",false);
    }

    @Test
    void get_all_customers_test() {
        customer_service.add_customer(test_customer1);
        customer_service.add_customer(test_customer2);

        given(customer_repository.findAll()).willReturn(List.of(test_customer1,test_customer2));

        List<Customer> customer_list = customer_service.get_all_customers();

        assertThat(customer_list).isNotNull();
        assertThat(customer_list.size()).isEqualTo(2);
    }

    @Test
    void get_customer_by_id_test() {
        customer_service.add_customer(test_customer1);

        given(customer_repository.getById(test_customer1.getCustomer_id())).willReturn(test_customer1);

        assertThat(customer_service.get_customer_by_id(test_customer1.getCustomer_id())).isEqualTo(test_customer1);
    }

    @Test
    void delete_customer_test() {
        customer_service.add_customer(test_customer1);

        customer_service.delete_customer(test_customer1.getCustomer_id());

        Customer deleted_customer = customer_service.get_customer_by_id(test_customer1.getCustomer_id());
        assertThat(deleted_customer).isNull();
    }

    @Test
    public void add_customer() {
        Customer saved_customer = customer_service.add_customer(test_customer1);

        assertThat(saved_customer).isNotNull();
    }

    @Test
    void authenticate() {
        Customer saved_customer = customer_service.add_customer(test_customer1);

        assertThat(customer_service.authenticate("tom_username", "tom_password")).isNotNull();
    }

    @Test
    void ban_hammer() {
        customer_service.add_customer(test_customer1);

        given(customer_repository.getById(1L)).willReturn(test_customer1);

        customer_service.ban_hammer(1L);

        assertThat(customer_service.get_customer_by_id(1L).isBanned()).isTrue();
    }

    @Test
    void update_customer() {
        customer_service.add_customer(test_customer1);

        given(customer_repository.getById(1L)).willReturn(test_customer1);

        Customer new_customer = new Customer(1l, "dave_username", "dave_password", "dave", "evad", "dave@gmail.com", false);
        customer_service.update_customer(new_customer);

        assertThat(customer_service.get_customer_by_id(1L)).isEqualTo(new_customer);
    }
}