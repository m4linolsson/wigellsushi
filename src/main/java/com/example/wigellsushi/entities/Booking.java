package com.example.wigellsushi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String startTime;
    @Column(length = 20, nullable = false)
    private String endTime;
    @Column(length = 3, nullable = false)
    private int numberOfGuests;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "BookingDishes",
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private List<Dish> dishes;

    @JsonIgnoreProperties("bookings")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Customer customer;

    @JsonIgnoreProperties("bookings")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Room room;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.0")
    private double totalPriceSek;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.0")
    private double totalPriceEur;

    public Booking() {
    }

    public Booking(String startTime, String endTime, int numberOfGuests, List<Dish> dishes, Customer customer, Room room, double totalPriceSek, double totalPriceEur) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfGuests = numberOfGuests;
        this.dishes = dishes;
        this.customer = customer;
        this.room = room;
        this.totalPriceSek = totalPriceSek;
        this.totalPriceEur = totalPriceEur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerOfBooking) {
        this.customer = customerOfBooking;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotalPriceSek() {
        return totalPriceSek;
    }


    public void setTotalPriceSek(double totalPriceSek) {
        this.totalPriceSek = totalPriceSek;
    }

    public double getTotalPriceEur() {
        return totalPriceEur;
    }


    public void setTotalPriceEur(double totalPriceEur) {
        this.totalPriceEur = totalPriceEur;
    }

}
