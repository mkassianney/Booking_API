package com.mkassianney.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.DTOs.ClientData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
@Table(name = "clients")
@Getter
@EqualsAndHashCode(of = "cpf")
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String cellphone;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Client(){}

    @JsonCreator
    public Client(@JsonProperty("value") @Valid ClientData clients) {
        this.name = clients.name();
        this.cpf = clients.cpf();
        this.email = clients.email();
        this.cellphone = clients.cellphone();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCellphone() {
        return cellphone;
    }
}
