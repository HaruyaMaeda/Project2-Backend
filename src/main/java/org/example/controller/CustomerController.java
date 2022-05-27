package org.example.controller;

import org.example.entity.Customer;
import org.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer){
        System.out.println("register request: " + customer);
//        return registeredCustomer == null ? "error_page" : "redirect:/login";
        return customerService.add_customer(customer);
    }

    @GetMapping("/login")
    public Customer login(@RequestParam String username, @RequestParam String password){
        return customerService.authenticate(username, password);
    }

    @GetMapping
    public List<Customer> get_all_customers(){
        return customerService.get_all_customers();
    }

    @GetMapping("/{id}")
    public Customer get_customer_by_id(@PathVariable("id") long id){
        return customerService.get_customer_by_id(id);
    }

    @PutMapping
    public Customer update_customer(@RequestBody Customer customer){
        return customerService.update_customer(customer);
    }

    @PutMapping("banHammer/{id}")
    public Customer ban_customer(@PathVariable("id") long id){ return customerService.ban_hammer(id);
    }

    @DeleteMapping("/{id_to_delete}")
    public void delete_customer(@PathVariable("id_to_delete") Long id){
        customerService.delete_customer(id);
    }

}
