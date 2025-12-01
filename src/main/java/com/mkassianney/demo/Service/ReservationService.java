package com.mkassianney.demo.Service;

import com.mkassianney.demo.DTOs.ReservationData;
import com.mkassianney.demo.DTOs.ReservationSimpleResponse;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Repository.ReservationRepository;
import com.mkassianney.demo.Repository.RoomRepository;
import com.mkassianney.demo.Model.Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RoomRepository roomR;
    @Autowired
    private ClientRepository clientR;

    public void createReservation(ReservationData data){
        Room room = roomR.findByRoomNumber(data.roomNumber())
                .orElseThrow(() -> new IllegalArgumentException("This room does´nt exist."));

        if (repository.existsConflict(data.roomNumber(), data.checkInDate(), data.checkOutDate())) {
            throw new IllegalArgumentException("Room is not available for these dates");
        }

        Client client = clientR.findByCpf(data.clientCpf())
                .orElseThrow(() -> new IllegalArgumentException("This client does´nt exist."));

        if (!room.isAvailable()) {
            throw new IllegalArgumentException("This room is´nt available.");
        }

        Reservation reservation = new Reservation(
                data,room,client
        );

        repository.save(reservation);

        roomR.save(room);

    }

    public List<ReservationSimpleResponse> existsReservation (Long id) throws Exception {
       boolean client = clientR.existsById(id);

        if(!client){
            throw new Exception("This client does not exist or does not have a reservation: " + id);
        }

        return repository.findListByClientId(id);

    }
}
