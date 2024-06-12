package com.example.wigellsushi;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;

import com.example.wigellsushi.entities.Takeaway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellSushiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WigellSushiApplication.class, args);
//        Logger logger = Logger.getLogger(WigellSushiApplication.class);
//        logger.log(Level.WARN, "slut");
        System.out.println("tweet tweet");


    }


}
