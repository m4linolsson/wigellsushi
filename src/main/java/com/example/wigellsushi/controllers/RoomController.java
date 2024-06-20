package com.example.wigellsushi.controllers;

import com.example.wigellsushi.entities.Room;
import com.example.wigellsushi.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
public class RoomController {
    @Autowired
    private RoomService roomService;


    @PutMapping("/updateroom/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

}
