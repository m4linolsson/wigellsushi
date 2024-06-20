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
@RequestMapping("/good")
public class controller {


    @GetMapping("/luck")
    public String goodLuck() {
        return "Lycka till på första dagen! Älskar dig :*";
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
        -ta bort rätt ***
        -uppdatera lokal***


          -lägga till loggning på allt ***
          - driftsätta.... wohooo***
          - API med priser**
          -koppla ihop med gateway/server**

- lägga till private på alla autowired***
-lägga till lenght**

TODO kolla så alla endopoint preauthorizer är i versaler!! ADMIN**

ev kolla närmare på delete funktionen, nu ändras ju inte totalpris fast att rätter tas bort...
     */
}
