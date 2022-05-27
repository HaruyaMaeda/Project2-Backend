package org.example.controller;

import org.example.entity.Cart;
import org.example.entity.Complaints;
import org.example.services.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/complaints")
public class ComplaintsController {
    @Autowired
    ComplaintsService complaintsService;

    @PostMapping
    public Complaints add_complaints(@RequestBody Complaints complaints) {return complaintsService.add_complaints(complaints);}

    @GetMapping("{id}")
    public Complaints get_complaints(@PathVariable("id") long id){return complaintsService.get_complaints_by_id(id);}

    @GetMapping("/customer/{id}")
    public List<Complaints> get_all_complaints_by_customer_id(@PathVariable("id") long id) {return complaintsService.get_all_complaints_by_customer_id(id);}

    @GetMapping
    public List<Complaints> get_all_complaints(){return complaintsService.get_all_complaints();}

    @PutMapping
    public Complaints update_complaints(@RequestBody Complaints complaints) {return complaintsService.update_complaints(complaints);}

    @DeleteMapping("{id}")
    public void delete_complaints(@PathVariable("id") long id) {complaintsService.delete_complaints(id);}
}
