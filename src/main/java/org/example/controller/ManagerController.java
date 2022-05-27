package org.example.controller;

import org.example.entity.Manager;
import org.example.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Manager add_manager(@RequestBody Manager manager){
        return managerService.add_manager(manager);
    }

    @GetMapping("/get")
    public List<Manager> get_all_managers(){
        return managerService.get_all_managers();
    }

    @GetMapping("/get/{id}")
    public Manager get_manager_by_id(@PathVariable("id") long id){
        return managerService.get_manager_by_id(id);
    }

    @PutMapping("/update/{id}")
    public Manager update_manager(@RequestBody Manager manager, @PathVariable("id") long id){
        return managerService.update_manager(manager, id);
    }

    @DeleteMapping("/delete/{id_to_delete}")
    public void delete_manager(@PathVariable("id_to_delete") Long id){
        managerService.delete_manager(id);
    }

}
