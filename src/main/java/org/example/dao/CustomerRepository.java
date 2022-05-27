package org.example.dao;

import org.example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
@CrossOrigin(origins="*")

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> getByUsernameAndPassword(String username, String password);

}
