package org.example.services;


import org.example.dao.ingredients.*;
import org.example.entity.Manager;
import org.example.entity.menu.Sandwich;
import org.example.entity.menu.ingredients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    BreadRepository breadRepository;

    @Autowired
    MeatRepository meatRepository;

    @Autowired
    SauceRepository sauceRepository;

    @Autowired
    SeasoningRepository seasoningRepository;

    @Autowired
    VegetableRepository vegetableRepository;

    public Bread add_bread(Bread bread) {
        breadRepository.save(bread);
        return bread;
    }

    public Bread get_bread_by_id(long id) {return breadRepository.getById(id);}

    public List<Bread> get_all_bread(){
        return breadRepository.findAll();
    }

    public Bread update_bread(Bread bread) {
        Bread breadDB = breadRepository.getById(bread.getId());
        breadDB.setName(bread.getName());
        breadDB.setPrice(bread.getPrice());
        breadRepository.save(bread);
        return bread;
    }

    public void delete_bread(long id) {breadRepository.deleteById(id);}

    //
    public Meat add_meat(Meat meat) {
        meatRepository.save(meat);
        return meat;
    }

    public Meat get_meat_by_id(Long id) {return meatRepository.getById(id);}

    public List<Meat> get_all_meat(){
        return meatRepository.findAll();
    }

    public Meat update_meat(Meat meat) {
        Meat meatDB = meatRepository.getById(meat.getId());
        meatDB.setName(meat.getName());
        meatDB.setPrice(meat.getPrice());
        meatRepository.save(meat);
        return meat;
    }

    public void delete_meat(Long id) {meatRepository.deleteById(id);}

    //
    public Sauce add_sauce(Sauce sauce) {
        sauceRepository.save(sauce);
        return sauce;
    }

    public Sauce get_sauce_by_id(Long id) {return sauceRepository.getById(id);}

    public List<Sauce> get_all_sauce(){
        return sauceRepository.findAll();
    }

    public Sauce update_sauce(Sauce sauce) {
        Sauce sauceDB = sauceRepository.getById(sauce.getId());
        sauceDB.setName(sauce.getName());
        sauceDB.setPrice(sauce.getPrice());
        sauceRepository.save(sauce);
        return sauce;
    }

    public void delete_sauce(Long id) {sauceRepository.deleteById(id);}

    //
    public Seasoning add_seasoning(Seasoning seasoning) {
        seasoningRepository.save(seasoning);
        return seasoning;
    }

    public Seasoning get_seasoning_by_id(Long id) {return seasoningRepository.getById(id);}

    public List<Seasoning> get_all_seasoning(){
        return seasoningRepository.findAll();
    }

    public Seasoning update_seasoning(Seasoning seasoning) {
        Seasoning seasoningDB = seasoningRepository.getById(seasoning.getId());
        seasoningDB.setName(seasoning.getName());
        seasoningDB.setPrice(seasoning.getPrice());
        seasoningRepository.save(seasoning);
        return seasoning;
    }

    public void delete_seasoning(Long id) {seasoningRepository.deleteById(id);}


    //
    public Vegetable add_vegetable(Vegetable vegetable) {
        vegetableRepository.save(vegetable);
        return vegetable;
    }

    public Vegetable get_vegetable_by_id(Long id) {return vegetableRepository.getById(id);}

    public List<Vegetable> get_all_vegetable(){
        return vegetableRepository.findAll();
    }

    public Vegetable update_vegetable(Vegetable vegetable) {
        Vegetable vegetableDB = vegetableRepository.getById(vegetable.getId());
        vegetableDB.setName(vegetable.getName());
        vegetableDB.setPrice(vegetable.getPrice());
        vegetableRepository.save(vegetable);
        return vegetable;
    }

    public void delete_vegetable(Long id) {vegetableRepository.deleteById(id);}


}
