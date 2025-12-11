package com.mkassianney.demo.Service;

import com.mkassianney.demo.Model.DTORequest.ReservationData;
import com.mkassianney.demo.Model.DTOResponse.ReservationSimpleResponse;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Repository.ReservationRepository;
import com.mkassianney.demo.Repository.RoomRepository;
import com.mkassianney.demo.Model.Entities.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RoomRepository roomR;
    @Autowired
    private ClientRepository clientR;

    @Transactional
    public void createReservation(ReservationData data){
        Room room = roomR.findByRoomNumber(data.roomNumber())
                .orElseThrow(() -> new IllegalArgumentException("This room does´nt exist."));

        boolean hasConflict = repository.existsConflict(data.roomNumber(), data.checkInDate(), data.checkOutDate());
        boolean isCanceled = repository.checkStatus(data.roomNumber());

        if (hasConflict && !isCanceled) {
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
