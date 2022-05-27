package org.example.dao;

import org.example.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customer_repository;

    Customer test_customer1;
    Customer test_customer2;

    @BeforeEach
    public void setup(){
        test_customer1 = new Customer(1l, "tom_username", "tom_password", "tom", "mot", "tom@gmail.com", false);
        test_customer2 = new Customer(2l, "mat_username", "mat_password", "mat", "tam", "mat@gmail.com",false);
    }

    @Test
    public void injectedComponentsAreNotNull(){
        Customer savedCustomer = customer_repository.save(test_customer1);

        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void findAllTest(){
        customer_repository.save(test_customer1);
        customer_repository.save(test_customer2);

        List<Customer> customerList = customer_repository.findAll();

        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(2);
    }

    @Test
    public void findByIdTest(){
        customer_repository.save(test_customer1);

        Customer customer_in_DB = customer_repository.findById(test_customer1.getCustomer_id()).get();

        assertThat(customer_in_DB).isNotNull();
    }

    @Test
    public void updateTest(){
        customer_repository.save(test_customer1);

        Customer saved_customer = customer_repository.findById(test_customer1.getCustomer_id()).get();
        saved_customer.setFirst_name("new_name");

        Customer updated_customer = customer_repository.save(saved_customer);

        assertThat(updated_customer.getFirst_name()).isEqualTo("new_name");

    }

    @Test
    public void deleteTest(){
        customer_repository.save(test_customer1);

        customer_repository.deleteById(test_customer1.getCustomer_id());

        Optional<Customer> deleted_customer = customer_repository.findById(test_customer1.getCustomer_id());
        assertThat(deleted_customer).isEmpty();
    }
}