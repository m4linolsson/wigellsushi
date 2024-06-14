package com.example.wigellsushi.controllers;

import com.example.wigellsushi.entities.Customer;
import com.example.wigellsushi.services.CustomerService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/customers")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }
}
