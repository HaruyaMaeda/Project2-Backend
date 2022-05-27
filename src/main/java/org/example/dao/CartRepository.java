package org.example.dao;

import org.example.entity.Cart;
import org.example.entity.menu.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM sandwich where cart_fk = ?1", nativeQuery = true)
    public List<Sandwich> get_all_sandwiches_by_cart_id(Long cart_id);

    //Finds all carts from a particular customer
    @Query(value = "SELECT * FROM cart where customer_id = ?1", nativeQuery = true)
    public List<Cart> getAllByCustomer_id(long id);

    @Query(value = "SELECT * FROM cart where employee_id = ?1", nativeQuery = true)
    public List<Cart> getAllByEmployee_id(long id);

    //"time" has format YYYY-MM-DD
    @Query(value = "SELECT * FROM cart\n" +
            "WHERE DATE(cart_date) = ?1", nativeQuery = true)
    public List<Cart> getAllByDate(Date time);

    public List<Cart> findAll();

    public Cart save(Cart cart);
}
