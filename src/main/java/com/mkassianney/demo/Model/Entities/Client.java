package com.mkassianney.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import mkassianney.api.model.ClientData;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity(name = "Client")
@Table(name = "client")
@Getter
@EqualsAndHashCode(of = "cpf")
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String cellphone;

    public Client(){}

    @JsonCreator
    public Client(@JsonProperty("value") ClientData clients) {
        this.name = clients.name();
        this.cpf = clients.cpf();
        this.email = clients.email();
        this.cellphone = clients.cellphone();
    }
}
