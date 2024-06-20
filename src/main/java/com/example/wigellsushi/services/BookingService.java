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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CurrencyConverterService currencyConverterService;
    private Logger logger = Logger.getLogger(BookingService.class);


    @Override
    public List<Booking> getMyBookings(Customer customer) {
        Optional<Customer> customerToListBookings = customerRepository.findByUserName(customer.getUserName());
        if (customerToListBookings.isPresent()) {
            return bookingRepository.findAllByCustomer_UserName(customer.getUserName());
        } else throw new ResourceNotFound("Customer", "username", customer.getUserName());
    }


    @Override
    public String bookRoom(Booking booking) {
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
            calculateAndSetTotalPrices(booking);
            bookingRepository.save(booking);
            logger.log(Level.WARN, "Customer with id: " + customer.getId() + " booked room with id: " + room.getId());
            return "Booking created";
        } else return "Failed to book room, maximum number of guests exceeded";
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
            calculateAndSetTotalPrices(booking);
            bookingRepository.save(bookingToUpdate);
            logger.log(Level.WARN, "Customer with id: " + customer.getId() + " updated booking with id: " + bookingToUpdate.getId());
            return "Booking updated";
        }
        return "Failed to update room, maximum number of guests exceeded";
    }


    private void calculateAndSetTotalPrices(Booking booking) {
        double totalPriceSek = booking.getDishes().stream().mapToDouble(Dish::getPrice).sum();
        booking.setTotalPriceSek(totalPriceSek);
        double totalPriceEur = currencyConverterService.convertSEKToEUR(totalPriceSek);
        booking.setTotalPriceEur(totalPriceEur);
    }


}
