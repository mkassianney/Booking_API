package com.mkassianney.demo.Service;

import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository repository;

    @Transactional
    public void deleteByNumber(int number) {
        boolean exists = repository.existsByRoomNumber(number);

        if (!exists) {
            throw new EntityNotFoundException("The chosen room do not exist: " + number);
        }

        repository.deleteByRoomNumber(number);
    }

    @Transactional
    public String toggleRoomAvailability(int number) {

        Room room = repository.findByRoomNumber(number)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        boolean newStatus = !room.isAvailable();
        room.setAvailable(newStatus);

        repository.save(room);

        return newStatus ?
                "Room is now available" :
                "Room is now unavailable";
    }

}
