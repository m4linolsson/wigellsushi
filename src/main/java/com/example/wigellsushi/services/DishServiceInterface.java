package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Dish;

import java.util.List;

public interface DishServiceInterface {

    List<Dish> getDishes();

    String addDish(Dish dish);

    String deleteDish(Long id);

}
