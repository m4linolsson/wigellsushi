package com.example.wigellsushi.repositories;

import com.example.wigellsushi.entities.Takeaway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeAwayRepository extends JpaRepository<Takeaway, Long> {
}
