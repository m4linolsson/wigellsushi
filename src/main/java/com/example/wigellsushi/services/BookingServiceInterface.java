package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Booking;
import com.example.wigellsushi.entities.Customer;

import java.util.List;

public interface BookingServiceInterface {

    //se bokningar
    List<Booking> getMyBookings(Customer customer);

   String bookRoom(Booking booking);

    String updateBooking(Long id, Booking booking);
}
