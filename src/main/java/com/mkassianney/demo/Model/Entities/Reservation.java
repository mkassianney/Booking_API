package com.mkassianney.demo.Model.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mkassianney.demo.Model.Enumerations.roomType;
import com.mkassianney.demo.DTOs.ReservationData;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Reservation")
@Table(name = "reservations")
@Getter
@EqualsAndHashCode(of = "id")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate check_in;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate check_out;
    private Period duration;
    private String client_name;
    private String client_cpf;
    private Long client_id;
    private String client_email;
    @Enumerated(EnumType.STRING)
    private roomType type;
    @Column(name = "number")
    private Integer number;
    private BigDecimal price;

    public Reservation(){}

    public Reservation(ReservationData reservation, Room room, Client client) {
        this.check_in = reservation.check_in();
        this.duration = reservation.duration();
        this.check_out = reservation.check_out();
        this.number = reservation.number();
        this.type = room.getRoomType();
        this.price = room.getPricePerNight();
        this.client_id = client.getId();
        this.client_cpf = client.getCpf();
        this.client_name = client.getName();
        this.client_email = client.getEmail();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCheckIn() {
        return check_in;
    }

    public Period getDuration(){return duration;}

    public LocalDate getCheckOut() {
        return check_out;
    }

    public roomType getType() {
        return type;
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public String getClientName(){
        return client_name;
    }
    public String getClientCpf(){
        return client_cpf;
    }
    public String getClientEmail(){
        return client_email;
    }
}

