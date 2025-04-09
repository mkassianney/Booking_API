package com.mkassianney.demo.Model.Service;

import com.mkassianney.demo.Model.DTOs.ReservationData;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Model.Repository.ReservationRepository;
import com.mkassianney.demo.Model.Repository.RoomRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RoomRepository roomR;

    public void createReservation(ReservationData data){
        Room room = roomR.findByRoomNumber(data.number())
                .orElseThrow(() -> new IllegalArgumentException("This room does´nt exist."));

        if (!room.isAvailable()) {
            throw new IllegalArgumentException("This room is´nt available.");
        }

        Reservation reservation = new Reservation(
                data,room
        );

        repository.save(reservation);

        room.setAvailable(false);
        roomR.save(room);

    }
}
