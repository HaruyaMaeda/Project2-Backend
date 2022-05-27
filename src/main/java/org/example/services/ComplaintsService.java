package org.example.services;

import org.example.dao.ComplaintsRepository;
import org.example.entity.Complaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintsService {
    
    @Autowired
    ComplaintsRepository complaintsRepository;
    
    public Complaints add_complaints(Complaints complaints) {
        complaintsRepository.save(complaints);
        return complaints;
    }

    public Complaints get_complaints_by_id(long id) {return complaintsRepository.findByComplaintsId(id);}

    public List<Complaints> get_all_complaints_by_customer_id(long id) {return complaintsRepository.getAllByCustomer_id(id);}

    public List<Complaints> get_all_complaints(){
        return complaintsRepository.findAll();
    }

    public Complaints update_complaints(Complaints complaints) {
        complaintsRepository.save(complaints);
        return complaints;
    }

    public void delete_complaints(long id) {complaintsRepository.deleteById(id);}
}
