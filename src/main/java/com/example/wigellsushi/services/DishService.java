package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Dish;
import com.example.wigellsushi.entities.Takeaway;
import com.example.wigellsushi.exceptions.ResourceNotFound;
import com.example.wigellsushi.repositories.DishRepository;
import com.example.wigellsushi.repositories.TakeAwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService implements DishServiceInterface {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    TakeAwayRepository takeAwayRepository;


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
        return "Added new dish";
    }

    @Override
    public String deleteDish(Long id) { //TODO kolla närare på, hur ska jag ta bort en rätt utan att to bort hela ordern...
        dishRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Dish", "id", id));

//kaos funkar inte
        for (Takeaway t : takeAwayRepository.findAll()) {
            for (Dish d : t.getDishes()) {
                if (d.getId().equals(id)) {
                    t.getDishes().remove(d);
                }
            }
        }


        dishRepository.deleteById(id);
        return "Dish with id: " + id + " deleted";
    }
}
