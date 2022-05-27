package org.example.services;

import org.example.dao.SandwichRepository;
import org.example.entity.menu.Sandwich;
import org.example.entity.menu.ingredients.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class SandwichServiceTest {
    @Mock
    SandwichRepository sandwichRepository;
    
    @InjectMocks
    SandwichService sandwichService;
    
    Sandwich sandwich1;
    Sandwich sandwich2;

    Bread bread1;
    Bread bread2;
    Meat meat1;
    Meat meat2;
    Vegetable vegetable1;
    Vegetable vegetable2;
    Sauce sauce1;
    Sauce sauce2;
    Seasoning seasoning1;
    Seasoning seasoning2;

    @BeforeEach
    public void setup(){
        bread1 = new Bread(1,"White",1);
        bread2 = new Bread(2,"Multigrain",2);
        meat1 = new Meat(1,"Beef", 1);
        meat2 = new Meat(2,"Chicken", 2);
        vegetable1 = new Vegetable(1,"Carrot",1);
        vegetable2 = new Vegetable(2,"Lettuce",2);
        sauce1 = new Sauce(1, "Ketchup", 1);
        sauce2 = new Sauce(2, "Mayonnaise", 2);
        seasoning1 = new Seasoning(1,"Salt",1);
        seasoning2 = new Seasoning(2,"Salt",2);

        sandwich1 = new Sandwich(bread1,meat1,vegetable1,sauce1,seasoning1);
        sandwich2 = new Sandwich(bread2,meat2,vegetable2,sauce2,seasoning2);
    }

    @Test
    void add_sandwich() {
        assertThat(sandwichService.add_sandwich(sandwich1)).isNotNull();
    }

    @Test
    void get_all_sandwiches() {
        sandwichService.add_sandwich(sandwich1);
        sandwichService.add_sandwich(sandwich2);

        given(sandwichRepository.findAll()).willReturn(List.of(sandwich1,sandwich2));

        List<Sandwich> sandwichList = sandwichService.get_all_sandwiches();

        assertThat(sandwichList).isNotNull();
        assertThat(sandwichList.size()).isEqualTo(2);
    }

    @Test
    void get_sandwich_by_id() {
        sandwichService.add_sandwich(sandwich1);

        given(sandwichRepository.getById(1L)).willReturn(sandwich1);

        assertThat(sandwichRepository.getById(1L)).isEqualTo(sandwich1);
    }

    @Test
    void update_sandwich() {
        Sandwich sandwich = sandwichService.add_sandwich(sandwich1);

        assertThat(sandwich).isNotNull();

        sandwich2 = new Sandwich(1L, bread2,meat2,vegetable2,sauce2,seasoning2);

        sandwichService.update_sandwich(sandwich2);

        given(sandwichRepository.getById(1L)).willReturn(sandwich2);
        sandwich = sandwichRepository.getById(1L);

        assertThat(sandwich).isEqualTo(sandwich2);
    }

    @Test
    void delete_sandwich() {
        sandwichService.add_sandwich(sandwich1);

        sandwichService.delete_sandwich(1L);

        assertThat(sandwichService.get_sandwich_by_id(1L)).isNull();
    }
}