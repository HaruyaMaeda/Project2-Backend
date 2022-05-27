package org.example.controller;


import org.example.entity.menu.Sandwich;
import org.example.entity.menu.ingredients.*;
import org.example.services.IngredientService;
import org.example.services.SandwichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sandwiches")
public class SandwichController {
    @Autowired
    SandwichService sandwichService;

    @Autowired
    IngredientService ingredientService;

    @PostMapping
    @ResponseBody
    public Sandwich add_sandwich(@RequestBody Sandwich sandwich){
        return sandwichService.add_sandwich(sandwich);
    }

    @GetMapping
    public List<Sandwich> get_all_sandwiches(){
        return sandwichService.get_all_sandwiches();
    }

    @GetMapping("/{id}")
    public Sandwich get_sandwich_by_id(@PathVariable("id") long id){
        return sandwichService.get_sandwich_by_id(id);
    }

    @PutMapping
    public Sandwich update_sandwich(@RequestBody Sandwich sandwich){
        return sandwichService.update_sandwich(sandwich);
    }

    @DeleteMapping("/{id_to_delete}")
    public void delete_sandwich(@PathVariable("id_to_delete") long id){
        sandwichService.delete_sandwich(id);
    }

}
