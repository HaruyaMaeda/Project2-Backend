package org.example.services;

import org.example.dao.ManagerRepository;
import org.example.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    public Manager add_manager(Manager manager){
        managerRepository.save(manager);
        System.out.println("Manager: " + manager.toString());
        return manager;
    }

    public List<Manager> get_all_managers(){
        return managerRepository.findAll();
    }

    public Manager get_manager_by_id(Long id){
        return managerRepository.getById(id);
    }

    public Manager update_manager(Manager manager, Long id){
        Manager managerDB = managerRepository.getById(manager.getManager_id());
        managerDB.setManager_name(manager.getManager_name());
        managerDB.setManager_id(manager.getManager_id());
        managerDB.setYears_of_experience(manager.getYears_of_experience());
        managerRepository.save(managerDB);
        return managerDB;
    }

    public void delete_manager(Long id){
        managerRepository.deleteById(id);
    }
}