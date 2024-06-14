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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Booking> getMyBookings(Customer customer) {
        Optional<Customer> customerToListBookings = customerRepository.findByUserName(customer.getUserName());
        if (customerToListBookings.isPresent()) {
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
            return "Booking updated";
        }
        return "Failed to update room, maximum number of guests exceeded";
    }
}
