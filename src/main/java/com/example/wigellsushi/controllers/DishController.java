package com.example.wigellsushi.controllers;

import com.example.wigellsushi.entities.Dish;
import com.example.wigellsushi.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/sushis")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Dish>> getDishes() {
        return ResponseEntity.ok(dishService.getDishes());
    }

    @PostMapping("/add-dish")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addDish(@RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.addDish(dish));
    }


    @DeleteMapping("/deletedish/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.deleteDish(id));
    }
}
