package com.example.wigellsushi.services;

import com.example.wigellsushi.entities.Room;
import com.example.wigellsushi.exceptions.ResourceNotFound;
import com.example.wigellsushi.repositories.RoomRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements RoomServiceInterface {
    @Autowired
    private RoomRepository roomRepository;
    private Logger logger = Logger.getLogger(RoomService.class);

    @Override
    public String updateRoom(Long id, Room room) {
        Room roomToUpdate = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Room", "id", id));
        roomToUpdate.setId(id);
        roomToUpdate.setEquipment(room.getEquipment());
        roomToUpdate.setMaxNumberOfGuests(room.getMaxNumberOfGuests());
        roomRepository.save(roomToUpdate);
        logger.log(Level.WARN, "Room with id: " + id + " has been updated");
        return "Room updated";
    }
}
