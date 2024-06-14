package com.example.wigellsushi.controllers;

import com.example.wigellsushi.entities.Booking;
import com.example.wigellsushi.entities.Customer;
import com.example.wigellsushi.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class BookingController {
    @Autowired
    BookingService bookingService;


    @GetMapping("/mybookings")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Booking>> getMyBookings(@RequestBody Customer customer) {
        return ResponseEntity.ok(bookingService.getMyBookings(customer));
    }

    @PostMapping("/bookroom")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> bookRoom(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.bookRoom(booking));
    }

    @PutMapping("/updatebooking/{id}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(id, booking));
    }
}
