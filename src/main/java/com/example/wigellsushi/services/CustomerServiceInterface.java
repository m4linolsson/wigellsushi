package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerServiceInterface {

    List<Customer> getCustomers();

}
