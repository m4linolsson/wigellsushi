package com.example.wigellsushi.repositories;

import com.example.wigellsushi.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
boolean existsByDishName(String dishName);
}
