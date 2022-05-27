package org.example.controller;


import org.example.dto.SandwichOrderIdObject;
import org.example.entity.Cart;
import org.example.entity.menu.Sandwich;
import org.example.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Cart add_cart(@RequestBody Cart cart){
        return cartService.add_cart(cart);
    }

    @PutMapping("/add")
    public Cart addSandwichToCart(@RequestBody SandwichOrderIdObject sandwichOrderIdObject) {
        Long sandwichId = sandwichOrderIdObject.getSandwich_id();
        Long cartId = sandwichOrderIdObject.getOrder_id();
        return cartService.add_sandwich_to_cart(sandwichId, cartId);
    }

    @GetMapping("/history/customer/{id}")
    public List<Cart> get_all_carts_by_customer_id(@PathVariable("id") long id) {return cartService.get_all_carts_by_customer_id(id);}

    @GetMapping("/history/employee/{id}")
    public List<Cart> get_all_carts_by_employee_id(@PathVariable("id") long id) {return cartService.get_all_carts_by_employee_id(id);}

    @GetMapping
    public List<Cart> get_all_carts(){
        return cartService.get_all_carts();
    }

    //"time" has format YYYY-MM-DD
    @GetMapping("/history/{time}")
    public List<Cart> get_all_carts_by_date(@PathVariable("time") Date time){
        return cartService.get_all_carts_by_date(time);
    }

    @GetMapping("/{id}")
    public Cart get_cart_by_id(@PathVariable("id") long id){
        return cartService.get_cart_by_id(id);
    }

    @PutMapping
    public Cart update_cart(@RequestBody Cart cart){
        return cartService.update_cart(cart);
    }

    @DeleteMapping("/{id_to_delete}")
    public void delete_cart(@PathVariable("id_to_delete") long id){
        cartService.delete_cart(id);
    }

    @GetMapping("/cost/{id}")
    public double get_sum_carts(@PathVariable("id") long id){
        return cartService.sum_cart(id);
    }

}
