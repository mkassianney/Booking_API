package com.mkassianney.demo.Controller;

import com.mkassianney.demo.DTOs.ClientData;
import com.mkassianney.demo.DTOs.ClientDataList;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private ClientService service;


    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid ClientData clients){
        repository.save(new Client(clients));
    }

    @GetMapping("/clientsList")
    public Page<ClientDataList> clientList(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        return repository.findAll(pageable).map(ClientDataList::from);
    }

    @GetMapping("/findClient/{cpf}")
    public Optional<ClientDataList> clients(@PathVariable String cpf){
        return repository.findByCpf(cpf).map(ClientDataList::from);
    }

    @DeleteMapping("/deleteClient/{cpf}")
    public ResponseEntity<Object> deleteClient(@PathVariable String cpf){
        service.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
