package com.example.wigellsushi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Takeaway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String timeForPickup;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.0")
    private double totalPrice;

    @JsonIgnoreProperties("bookings")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Customer customer;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Takeaway_Dishes",
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private List<Dish> dishes;

    public Takeaway() {
    }

    public Takeaway(String timeForPickup, double totalPrice, Customer customer, List<Dish> dishes) {
        this.timeForPickup = timeForPickup;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.dishes = dishes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getTimeForPickup() {
        return timeForPickup;
    }

    public void setTimeForPickup(String timeForPickup) {
        this.timeForPickup = timeForPickup;
    }

    public double getTotalPrice() {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
