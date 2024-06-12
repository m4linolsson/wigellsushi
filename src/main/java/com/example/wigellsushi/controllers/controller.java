package com.example.wigellsushi.controllers;

import com.example.wigellsushi.entities.Booking;
import com.example.wigellsushi.entities.Takeaway;
import com.example.wigellsushi.repositories.BookingRepository;
import com.example.wigellsushi.repositories.DishRepository;
import com.example.wigellsushi.repositories.TakeAwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sushi")
public class controller {


    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    DishRepository dishRepository;

    @Autowired
    TakeAwayRepository takeAwayRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/takeaway")
    public List<Takeaway> getTakeaway() {
        return takeAwayRepository.findAll();
    }


    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }
}
