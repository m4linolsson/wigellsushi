package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Room;
import com.example.wigellsushi.exceptions.ResourceNotFound;
import com.example.wigellsushi.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements RoomServiceInterface {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public String updateRoom(Long id, Room room) {
        // om rummet finns spara det..


        Room roomToUpdate = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Room", "id", id));
        roomToUpdate.setId(id);
        roomToUpdate.setEquipment(room.getEquipment());
        roomToUpdate.setMaxNumberOfGuests(room.getMaxNumberOfGuests());
        roomRepository.save(roomToUpdate);
        return "Room updated";

    }
}
