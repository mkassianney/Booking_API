package com.mkassianney.demo.Controller;

import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Model.ReservationData;
import com.mkassianney.demo.Model.RoomData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.ClientData;
import com.mkassianney.demo.Model.Repository.ClientRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class Register {

    @Autowired
    private ClientRepository repository;

    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid ClientData clients){
        repository.save(new Client(clients));
    }

}
