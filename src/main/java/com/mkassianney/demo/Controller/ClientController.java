package com.mkassianney.demo.Controller;

import com.mkassianney.demo.DTOs.ClientData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Service.ReservationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid ClientData clients){
        repository.save(new Client(clients));
    }
}
