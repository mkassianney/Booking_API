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
import java.time.temporal.ChronoUnit;

@Entity(name = "Reservation")
@Table(name = "reservations")
@Getter
@EqualsAndHashCode(of = "id")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "check_in")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @Column(name = "check_out")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
    private int duration;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    @Column(name = "room_number")
    private Integer roomNumber;
    private BigDecimal price;

    public Reservation(){}

    public Reservation(ReservationData reservation, Room room, Client client) {
        this.checkInDate = reservation.checkInDate();
        this.checkOutDate = reservation.checkOutDate();
        this.duration = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        this.roomNumber = reservation.roomNumber();
        this.price = room.getPricePerNight();
        this.client = client;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCheckIn() {
        return checkInDate;
    }

    public int getDuration(){return duration;}

    public LocalDate getCheckOut() {
        return checkOutDate;
    }

    public Integer getNumber() {
        return roomNumber;
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

