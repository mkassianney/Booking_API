package com.mkassianney.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.Model.DTORequest.ClientData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
@Table(name = "clients")
@Getter
@EqualsAndHashCode(of = "cpf")
@NoArgsConstructor
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String cellphone;
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();

    @JsonCreator
    public Client(@JsonProperty("value") @Valid ClientData clients) {
        this.name = clients.name();
        this.cpf = clients.cpf();
        this.email = clients.email();
        this.cellphone = clients.cellphone();
    }

}
