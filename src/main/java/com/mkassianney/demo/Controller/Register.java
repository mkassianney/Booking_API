package com.mkassianney.demo.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.mkassianney.demo.Model.Entities.Client;
import mkassianney.api.model.ClientData;
import com.mkassianney.demo.Model.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class Register {

    @Autowired
    private ClientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid ClientData clients){
        repository.save(new Client(clients));
    }
}
