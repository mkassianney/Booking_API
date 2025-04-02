package com.mkassianney.demo.Controller;

import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Model.Repository.ReservationRepository;
import com.mkassianney.demo.Model.Repository.RoomRepository;
import com.mkassianney.demo.Model.DTOs.ReservationData;
import com.mkassianney.demo.Model.DTOs.RoomData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.DTOs.ClientData;
import com.mkassianney.demo.Model.Repository.ClientRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class Register {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid ClientData clients){
        repository.save(new Client(clients));
    }

    @PostMapping("/newRoom")
    @Transactional
    public void newRoom(@RequestBody @Valid RoomData roomData){
        roomRepository.save(new Room(roomData));
    }

    @PostMapping("/newReservation")
    @Transactional
    public void newReservation(@RequestBody @Valid ReservationData reservationData, Room r){
        reservationRepository.save(new Reservation(reservationData, r.getRoomNumber(),r.getRoomType(),r.getPricePerNight()));
    }

    @GetMapping("/roomList")
    public List<RoomData> roomList(){
        return roomRepository.findAll().stream().map(RoomData::new).toList();
    }

}
