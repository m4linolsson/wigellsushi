package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Booking;
import com.example.wigellsushi.entities.Dish;
import com.example.wigellsushi.entities.Takeaway;
import com.example.wigellsushi.exceptions.ResourceNotFound;
import com.example.wigellsushi.repositories.BookingRepository;
import com.example.wigellsushi.repositories.DishRepository;
import com.example.wigellsushi.repositories.TakeAwayRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService implements DishServiceInterface {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private TakeAwayRepository takeAwayRepository;
    @Autowired
    private BookingRepository bookingRepository;

    private Logger logger = Logger.getLogger(DishService.class);

    @Override
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    @Override
    public String addDish(Dish dish) {
        if (dishRepository.existsByDishName(dish.getDishName())) {
            return "Failed to add dish, name is taken";
        }
        dishRepository.save(dish);
        logger.log(Level.WARN, "Dish with id: " + dish.getId() + " added");
        return "Added new dish";
    }

    @Override
    public String deleteDish(Long id) {
        Dish dishToRemove = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Dish", "id", id));

        for (Takeaway takeaway : takeAwayRepository.findAll()) {
            takeaway.getDishes().removeIf(dish -> dish.equals(dishToRemove));
            takeAwayRepository.save(takeaway);
        }

        for (Booking booking : bookingRepository.findAll()) {
            booking.getDishes().removeIf(dish -> dish.equals(dishToRemove));
            bookingRepository.save(booking);
        }
        dishRepository.delete(dishToRemove);

        logger.log(Level.WARN, "Dish with id: " + id + " deleted");
        return "Dish with id: " + id + " deleted";
    }
}
