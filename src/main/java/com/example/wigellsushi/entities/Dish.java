package com.example.wigellsushi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
@Entity
public class Dish {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 25,nullable = false)

    private String dishName;
    @Column (nullable = false)
    private double price;

    public Dish() {
    }

    public Dish(String dishName, double price) {
        this.dishName = dishName;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
