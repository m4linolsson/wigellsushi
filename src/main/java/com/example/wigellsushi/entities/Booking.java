package com.example.wigellsushi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.persistence.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//TODO stuntar i att kolla om den är bokad eller inte, kan bli dubbelbokning nu men inte där fokus är...
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startTime;
    private String endTime;

    private int numberOfGuests;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
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
    private double totalPrice;

//    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.0")
//    private double totalPriceEUR;
    public Booking() {
    }

    public Booking(String startTime, String endTime, int numberOfGuests, List<Dish> dishes, Customer customerOfBooking, Room room, double totalPrice) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfGuests = numberOfGuests;
        this.dishes = dishes;
        this.customer = customerOfBooking;
        this.room = room;
        this.totalPrice = totalPrice;
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

    public double getTotalPrice() {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

//
//    public double getTotalPriceEUR() {
//        return totalPriceEUR;
//    }
//
//    public void setTotalPriceEUR(double totalPriceEUR) {
//        this.totalPriceEUR = totalPriceEUR;
//    }
}
