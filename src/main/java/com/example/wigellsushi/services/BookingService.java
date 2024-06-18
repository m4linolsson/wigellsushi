package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Booking;
import com.example.wigellsushi.entities.Customer;
import com.example.wigellsushi.entities.Dish;
import com.example.wigellsushi.entities.Room;
import com.example.wigellsushi.exceptions.ResourceNotFound;
import com.example.wigellsushi.repositories.BookingRepository;
import com.example.wigellsushi.repositories.CustomerRepository;
import com.example.wigellsushi.repositories.DishRepository;
import com.example.wigellsushi.repositories.RoomRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    DishRepository dishRepository;


    Logger logger = Logger.getLogger(BookingService.class);

//    @Override
//    public List<Booking> getMyBookings(Customer customer) {
//        Optional<Customer> customerToListBookings = customerRepository.findByUserName(customer.getUserName());
//        if (customerToListBookings.isPresent()) {
//            return bookingRepository.findAllByCustomer_UserName(customer.getUserName());
//        } else throw new ResourceNotFound("Customer", "username", customer.getUserName());
//    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Booking> getMyBookings(Customer customer) {
        Optional<Customer> customerToListBookings = customerRepository.findByUserName(customer.getUserName());
        if (customerToListBookings.isPresent()) {

          //  System.out.println(restTemplate.getForObject("http://localhost:7070/v6/1c5fb295f62bb439ba29f893/pair/EUR/GBP", String.class));
            System.out.println(restTemplate.getForObject("http://WIGELL-TRAVEL-GATEWAY/v6/1c5fb295f62bb439ba29f893/pair/EUR/GBP", String.class));
//            JSONPObject jp=
////            JsonObjectDeserializer jsonObjectDeserializer=
//          String converter  restTemplate.getForObject("http://WIGELL-TRAVEL-GATEWAY/v6/1c5fb295f62bb439ba29f893/pair/EUR/GBP", JSONPObject.class);

            System.out.println("price in euro: "); // ev ha price som map

            return bookingRepository.findAllByCustomer_UserName(customer.getUserName());
        } else throw new ResourceNotFound("Customer", "username", customer.getUserName());
    }

    @Override
    public String bookRoom(Booking booking) {
        //TODO, gjort det så simpelt som möjligt, kollar ingenting och kan bli dubbelbokningar nu

        Customer customer = customerRepository.findById(booking.getCustomer().getId()).orElseThrow(() -> new ResourceNotFound("Customer", "id", booking.getCustomer().getId()));
        Room room = roomRepository.findById(booking.getRoom().getId()).orElseThrow(() -> new ResourceNotFound("Room", "id", booking.getRoom().getId()));

        List<Dish> dishes = new ArrayList<>();
        for (Dish d : booking.getDishes()) {
            Dish dish = dishRepository.findById(d.getId()).orElseThrow(() -> new ResourceNotFound("Dish", "id", d.getId()));
            dishes.add(dish);
        }

        if (room.getMaxNumberOfGuests() >= booking.getNumberOfGuests()) {
            booking.setCustomer(customer);
            booking.setRoom(room);
            booking.setDishes(dishes);
            bookingRepository.save(booking);
            logger.log(Level.WARN, "Customer with id: " + customer.getId() + " booked room with id: " + room.getId());
            return "Booking created";
        }
        return "Failed to book room, maximum number of guests exceeded";
    }

    @Override
    public String updateBooking(Long id, Booking booking) {
        Booking bookingToUpdate = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Booking", "id", id));

        Customer customer = customerRepository.findById(booking.getCustomer().getId()).orElseThrow(() -> new ResourceNotFound("Customer", "id", booking.getCustomer().getId()));
        Room room = roomRepository.findById(booking.getRoom().getId()).orElseThrow(() -> new ResourceNotFound("Room", "id", booking.getRoom().getId()));

        List<Dish> dishes = new ArrayList<>();
        for (Dish d : booking.getDishes()) {
            Dish dish = dishRepository.findById(d.getId()).orElseThrow(() -> new ResourceNotFound("Dish", "id", d.getId()));
            dishes.add(dish);
        }
        if (room.getMaxNumberOfGuests() >= booking.getNumberOfGuests()) {
            bookingToUpdate.setStartTime(booking.getStartTime());
            bookingToUpdate.setEndTime(booking.getEndTime());
            bookingToUpdate.setNumberOfGuests(booking.getNumberOfGuests());
            bookingToUpdate.setCustomer(customer);
            bookingToUpdate.setRoom(room);
            bookingToUpdate.setDishes(dishes);
            bookingRepository.save(bookingToUpdate);
            logger.log(Level.WARN, "Customer with id: " + customer.getId() + " updated booking with id: " + bookingToUpdate.getId());
            return "Booking updated";
        }
        return "Failed to update room, maximum number of guests exceeded";
    }

}
