package org.example.dao;

import org.example.entity.menu.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SandwichRepository extends JpaRepository<Sandwich, Long> {
    @Query(value = "SELECT * FROM sandwich where sandwich_id = ?1", nativeQuery = true)
    public Sandwich get_sandwich_by_id(Long sandwich_id);


}