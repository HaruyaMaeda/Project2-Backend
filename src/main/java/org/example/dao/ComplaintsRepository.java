package org.example.dao;


import org.example.entity.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintsRepository extends JpaRepository<Complaints, Long> {

    @Query(value = "SELECT * FROM complaints where id = ?1", nativeQuery = true)
    public Complaints findByComplaintsId(long id);

    @Query(value = "SELECT * FROM complaints where customer_id = ?1", nativeQuery = true)
    public List<Complaints> getAllByCustomer_id(long id);
}
