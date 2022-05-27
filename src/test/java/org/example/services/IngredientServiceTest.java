package org.example.services;

import org.example.dao.ingredients.*;
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
class IngredientServiceTest {
    
    @Mock
    BreadRepository breadRepository;
    @Mock
    MeatRepository meatRepository;
    @Mock
    VegetableRepository vegetableRepository;
    @Mock
    SauceRepository sauceRepository;
    @Mock
    SeasoningRepository seasoningRepository;

    @InjectMocks
    IngredientService ingredientService;
    
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
        bread1 = new Bread(1L,"White",1);
        bread2 = new Bread(2L,"Multigrain",2);
        meat1 = new Meat(1L,"Beef", 1);
        meat2 = new Meat(2L,"Chicken", 2);
        vegetable1 = new Vegetable(1L,"Carrot",1);
        vegetable2 = new Vegetable(2L,"Lettuce",2);
        sauce1 = new Sauce(1L, "Ketchup", 1);
        sauce2 = new Sauce(2L, "Mayonnaise", 2);
        seasoning1 = new Seasoning(1L,"Salt",1);
        seasoning2 = new Seasoning(2L,"Salt",2);
    }

    @Test
    void add_bread() {
        assertThat(ingredientService.add_bread(bread1)).isNotNull();
    }

    @Test
    void get_bread_by_id() {
        ingredientService.add_bread(bread1);
        given(breadRepository.getById(1L)).willReturn(bread1);
        assertThat(breadRepository.getById(1L)).isEqualTo(bread1);
    }

    @Test
    void get_all_bread() {
        ingredientService.add_bread(bread1);
        ingredientService.add_bread(bread2);

        given(breadRepository.findAll()).willReturn(List.of(bread1,bread2));

        List<Bread> breadList = ingredientService.get_all_bread();

        assertThat(breadList).isNotNull();
        assertThat(breadList.size()).isEqualTo(2);
    }

    @Test
    void update_bread() {
        ingredientService.add_bread(bread1);
        given(breadRepository.getById(1L)).willReturn(bread1);
        assertThat(ingredientService.get_bread_by_id(1L)).isEqualTo(bread1);
        bread2.setId(1L);
        ingredientService.update_bread(bread2);

        assertThat(ingredientService.get_bread_by_id(1L)).isEqualTo(bread2);
    }

    @Test
    void delete_bread() {
        ingredientService.add_bread(bread1);
        ingredientService.delete_bread(1L);
        Bread bread = ingredientService.get_bread_by_id(1L);
        assertThat(bread).isNull();
    }

    @Test
    void add_meat() {
        assertThat(ingredientService.add_meat(meat1)).isNotNull();
    }

    @Test
    void get_meat_by_id() {
        ingredientService.add_meat(meat1);
        given(meatRepository.getById(1L)).willReturn(meat1);
        assertThat(meatRepository.getById(1L)).isEqualTo(meat1);
    }

    @Test
    void get_all_meat() {
        ingredientService.add_meat(meat1);
        ingredientService.add_meat(meat2);

        given(meatRepository.findAll()).willReturn(List.of(meat1,meat2));

        List<Meat> meatList = ingredientService.get_all_meat();

        assertThat(meatList).isNotNull();
        assertThat(meatList.size()).isEqualTo(2);
    }

    @Test
    void update_meat() {
        ingredientService.add_meat(meat1);
        given(meatRepository.getById(1L)).willReturn(meat1);
        assertThat(ingredientService.get_meat_by_id(1L)).isEqualTo(meat1);
        meat2.setId(1L);
        ingredientService.update_meat(meat2);

        assertThat(ingredientService.get_meat_by_id(1L)).isEqualTo(meat2);
    }

    @Test
    void delete_meat() {
        ingredientService.add_meat(meat1);
        ingredientService.delete_meat(1L);
        Meat meat = ingredientService.get_meat_by_id(1L);
        assertThat(meat).isNull();
    }

    @Test
    void add_sauce() {
        assertThat(ingredientService.add_sauce(sauce1)).isNotNull();
    }

    @Test
    void get_sauce_by_id() {
        ingredientService.add_sauce(sauce1);
        given(sauceRepository.getById(1L)).willReturn(sauce1);
        assertThat(ingredientService.get_sauce_by_id(1L)).isEqualTo(sauce1);
    }

    @Test
    void get_all_sauce() {
        ingredientService.add_sauce(sauce1);
        ingredientService.add_sauce(sauce2);

        given(sauceRepository.findAll()).willReturn(List.of(sauce1,sauce2));

        List<Sauce> sauceList = ingredientService.get_all_sauce();

        assertThat(sauceList).isNotNull();
        assertThat(sauceList.size()).isEqualTo(2);
    }

    @Test
    void update_sauce() {
        ingredientService.add_sauce(sauce1);
        given(sauceRepository.getById(1L)).willReturn(sauce1);
        assertThat(ingredientService.get_sauce_by_id(1L)).isEqualTo(sauce1);
        sauce2.setId(1L);
        ingredientService.update_sauce(sauce2);

        assertThat(ingredientService.get_sauce_by_id(1L)).isEqualTo(sauce2);
    }

    @Test
    void delete_sauce() {
        ingredientService.add_sauce(sauce1);
        ingredientService.delete_sauce(1L);
        Sauce sauce = ingredientService.get_sauce_by_id(1L);
        assertThat(sauce).isNull();
    }

    @Test
    void add_seasoning() {
        assertThat(ingredientService.add_seasoning(seasoning1)).isNotNull();
    }

    @Test
    void get_seasoning_by_id() {
        ingredientService.add_seasoning(seasoning1);
        given(seasoningRepository.getById(1L)).willReturn(seasoning1);
        assertThat(seasoningRepository.getById(1L)).isEqualTo(seasoning1);
    }

    @Test
    void get_all_seasoning() {
        ingredientService.add_seasoning(seasoning1);
        ingredientService.add_seasoning(seasoning2);

        given(seasoningRepository.findAll()).willReturn(List.of(seasoning1,seasoning2));

        List<Seasoning> seasoningList = ingredientService.get_all_seasoning();

        assertThat(seasoningList).isNotNull();
        assertThat(seasoningList.size()).isEqualTo(2);
    }

    @Test
    void update_seasoning() {
        ingredientService.add_seasoning(seasoning1);
        given(seasoningRepository.getById(1L)).willReturn(seasoning1);
        assertThat(ingredientService.get_seasoning_by_id(1L)).isEqualTo(seasoning1);
        seasoning2.setId(1L);
        ingredientService.update_seasoning(seasoning2);

        assertThat(ingredientService.get_seasoning_by_id(1L)).isEqualTo(seasoning2);
    }

    @Test
    void delete_seasoning() {
        ingredientService.add_seasoning(seasoning1);
        ingredientService.delete_seasoning(1L);
        Seasoning seasoning = ingredientService.get_seasoning_by_id(1L);
        assertThat(seasoning).isNull();
    }

    @Test
    void add_vegetable() {
        assertThat(ingredientService.add_vegetable(vegetable1)).isNotNull();
    }

    @Test
    void get_vegetable_by_id() {
        ingredientService.add_vegetable(vegetable1);
        given(vegetableRepository.getById(1L)).willReturn(vegetable1);
        assertThat(vegetableRepository.getById(1L)).isEqualTo(vegetable1);
    }

    @Test
    void get_all_vegetable() {
        ingredientService.add_vegetable(vegetable1);
        ingredientService.add_vegetable(vegetable2);

        given(vegetableRepository.findAll()).willReturn(List.of(vegetable1,vegetable2));

        List<Vegetable> vegetableList = ingredientService.get_all_vegetable();

        assertThat(vegetableList).isNotNull();
        assertThat(vegetableList.size()).isEqualTo(2);
    }

    @Test
    void update_vegetable() {
        ingredientService.add_vegetable(vegetable1);
        given(vegetableRepository.getById(1L)).willReturn(vegetable1);
        assertThat(ingredientService.get_vegetable_by_id(1L)).isEqualTo(vegetable1);
        vegetable2.setId(1L);
        ingredientService.update_vegetable(vegetable2);

        assertThat(ingredientService.get_vegetable_by_id(1L)).isEqualTo(vegetable2);
    }

    @Test
    void delete_vegetable() {
        ingredientService.add_vegetable(vegetable1);
        ingredientService.delete_vegetable(1L);
        Vegetable vegetable = ingredientService.get_vegetable_by_id(1L);
        assertThat(vegetable).isNull();
    }

}