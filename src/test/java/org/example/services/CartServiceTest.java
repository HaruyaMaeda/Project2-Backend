package org.example.services;


import org.example.dao.CartRepository;
import org.example.dao.SandwichRepository;
import org.example.entity.Cart;
import org.example.entity.menu.Sandwich;
import org.example.entity.menu.ingredients.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@DataJpaTest
class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    SandwichRepository sandwichRepository;

    @InjectMocks
    SandwichService sandwichService;

    @InjectMocks
    CartService cartService;

    Cart test_cart1;
    Cart test_cart2;

    Sandwich sandwich1;
    Sandwich sandwich2;

    @BeforeEach
    public void setup(){
        test_cart1 = new Cart(1L,1L,1L,10.00,Timestamp.valueOf("2222-01-14 09:01:16"));
        test_cart2 = new Cart(2L,2L,2L,20.00,Timestamp.valueOf("2222-01-14 09:01:16"));

        Bread bread1 = new Bread(1L,"White",1);
        Bread bread2 = new Bread(2L,"Multigrain",2);
        Meat meat1 = new Meat(1L,"Beef", 1);
        Meat meat2 = new Meat(2L,"Chicken", 2);
        Vegetable vegetable1 = new Vegetable(1,"Carrot",1);
        Vegetable vegetable2 = new Vegetable(2,"Lettuce",2);
        Sauce sauce1 = new Sauce(1L, "Ketchup", 1);
        Sauce sauce2 = new Sauce(2L, "Mayonnaise", 2);
        Seasoning seasoning1 = new Seasoning(1L,"Salt",1);
        Seasoning seasoning2 = new Seasoning(2L,"Salt",2);

        sandwich1 = new Sandwich(bread1,meat1,vegetable1,sauce1,seasoning1);
        sandwich2 = new Sandwich(bread2,meat2,vegetable2,sauce2,seasoning2);
    }

    @Test
    void add_cart() {
        assertThat(cartService.add_cart(test_cart1)).isNotNull();
    }

    @Test
    void get_all_carts() {
        cartService.add_cart(test_cart1);
        cartService.add_cart(test_cart2);

        given(cartRepository.findAll()).willReturn(List.of(test_cart1,test_cart2));

        List<Cart> cartList = cartService.get_all_carts();

        assertThat(cartList).isNotNull();
        assertThat(cartList.size()).isEqualTo(2);
    }

    @Test
    void get_cart_by_id() {
        cartService.add_cart(test_cart1);

        given(cartRepository.getById(1l)).willReturn(test_cart1);

        assertThat(cartService.get_cart_by_id(1l)).isEqualTo(test_cart1);
    }

    @Test
    void update_cart() {
        Set<Sandwich> sandwichSet = new HashSet<>();
        sandwichSet.add(sandwich1);
        sandwichSet.add(sandwich2);

        Cart test_cart3 = new Cart(1L,6,6,12.00,Timestamp.valueOf("2222-01-14 09:01:16"),sandwichSet);
        cartService.add_cart(test_cart1);

        given(cartRepository.getById(1L)).willReturn(test_cart1);
        Cart cart_save = cartRepository.getById(1L);

        assertThat(cart_save).isEqualTo(test_cart1);

        cartService.update_cart(test_cart3);

        given(cartRepository.getById(1L)).willReturn(test_cart3);
        cart_save = cartService.get_cart_by_id(1L);

        assertThat(cart_save).isEqualTo(test_cart3);

    }

    @Test
    void delete_cart() {
        cartService.add_cart(test_cart1);

        cartService.delete_cart(1L);

        Cart cart = cartService.get_cart_by_id(1L);
        assertThat(cart).isNull();
    }

    @Test
    void sum_cart() {
        Cart cart = cartService.add_cart(test_cart1);
        cart.addSandwich(sandwich1);
        cart.addSandwich(sandwich2);

        given(cartRepository.getById(cart.getCart_id())).willReturn(test_cart1);

        cartService.sum_cart(cart.getCart_id());

        assertThat(cart.getCost_sum()).isEqualTo(15.00);
    }

    @Test
    void add_sandwich_to_cart() {
        Cart cart = cartService.add_cart(test_cart1);

        sandwichService.add_sandwich(sandwich2);

        given(cartRepository.getById(cart.getCart_id())).willReturn(cart);
        cartService.add_sandwich_to_cart(1L,1L);

        cart = cartService.get_cart_by_id(1L);

        assertThat(cart.getSandwiches().size()).isEqualTo(1);
    }

    @Test
    void remove_sandwich_to_cart() {
        Cart cart = cartService.add_cart(test_cart1);
        sandwichService.add_sandwich(sandwich1);
        sandwichService.add_sandwich(sandwich2);

        given(cartRepository.getById(cart.getCart_id())).willReturn(cart);

        cartService.add_sandwich_to_cart(1L,1L);

        cart = cartService.get_cart_by_id(1L);

        assertThat(cart.getSandwiches().size()).isEqualTo(1);

        cartService.remove_sandwich_to_cart(2L,1L);

        cart = cartService.get_cart_by_id(1L);

        assertThat(cart.getSandwiches().size()).isEqualTo(0);
    }

    @Test
    void get_all_carts_by_customer_id() {
        cartService.add_cart(test_cart1);
        cartService.add_cart(test_cart2);

        given(cartRepository.getAllByCustomer_id(2l)).willReturn(List.of(test_cart2));

        assertThat(cartService.get_all_carts_by_customer_id(2L).size()).isEqualTo(1);
        
    }

    @Test
    void get_all_carts_by_employee_id() {
        cartService.add_cart(test_cart1);
        cartService.add_cart(test_cart2);

        given(cartRepository.getAllByEmployee_id(2l)).willReturn(List.of(test_cart2));

        assertThat(cartService.get_all_carts_by_employee_id(2L).size()).isEqualTo(1);
    }

    @Test
    void get_all_carts_by_date() {
        cartService.add_cart(test_cart1);
        cartService.add_cart(test_cart2);

        given(cartRepository.getAllByDate(Date.valueOf("2222-01-14"))).willReturn(List.of(test_cart1, test_cart2));

        assertThat(cartService.get_all_carts_by_date(Date.valueOf("2222-01-14")).size()).isEqualTo(2);
    }
}