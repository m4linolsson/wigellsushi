package com.example.wigellsushi.controllers;

import com.example.wigellsushi.entities.Booking;
import com.example.wigellsushi.entities.Takeaway;
import com.example.wigellsushi.repositories.BookingRepository;
import com.example.wigellsushi.repositories.DishRepository;
import com.example.wigellsushi.repositories.TakeAwayRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(controller.class);

    @GetMapping("/welcome")
    public String welcome() {
        logger.log(Level.FATAL, "test");
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


    /* TODO
        KUNDER
        -lista rätter ***
        -reservera lokal/rum (boka rum)***
        -uppdatera bokning (rum)***
        - se tidigare och aktiva bokningar***
        ADMIN
        -lista kunder ***
        - lägga till maträtt ***
        -ta bort rätt ERROR- kopplingarna emellan är fel...
        -uppdatera lokal***


          -lägga till loggning på allt
          - driftsätta....
          - API med priser
     */
}
