package com.mkassianney.demo.Model.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mkassianney.demo.Model.Enumerations.RoomType;
import com.mkassianney.demo.DTOs.ReservationData;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    @Enumerated(EnumType.STRING)
    private RoomType type;
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
        this.client = client;
        this.room = room;
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

    public RoomType getType() {
        return type;
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }
}

