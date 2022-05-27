package org.example.dao.ingredients;

import org.example.entity.menu.ingredients.Meat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// we specify that our repository is for the Ingredient object with id's being stored as longs
@Repository
public interface MeatRepository extends JpaRepository<Meat, Long> {

    // the program knows that we want to find all Ingredients with years greater than whatever we pass in:

    // making our own custom query using postres syntax:
    @Query(value = "SELECT * FROM meat where id = ?1", nativeQuery = true)
    public Meat findByMeat_id(Long meat_id);
}