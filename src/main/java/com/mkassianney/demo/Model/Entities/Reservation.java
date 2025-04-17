package com.mkassianney.demo.Model.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mkassianney.demo.Model.Enumerations.roomType;
import com.mkassianney.demo.Model.DTOs.ReservationData;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Reservation")
@Table(name = "reservation")
@Getter
@EqualsAndHashCode(of = "id")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;
    @Enumerated(EnumType.STRING)
    private roomType type;
    @Column(name = "number")
    private Integer number;
    private BigDecimal price;

    public Reservation(){}

    public Reservation(ReservationData reservation, Room room) {
        this.checkIn = reservation.checkIn();
        this.checkOut = reservation.checkOut();
        this.number = reservation.number();
        this.type = room.getRoomType();
        this.price = room.getPricePerNight();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
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
}

