package com.mkassianney.demo.Model.Entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.Model.Enumerations.roomType;
import com.mkassianney.demo.Model.DTOs.ReservationData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@NoArgsConstructor
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
    private int number;
    private BigDecimal price;

    public Reservation(ReservationData reservation, Room room) {
        this.checkIn = reservation.checkIn();
        this.checkOut = reservation.checkOut();
        this.number = reservation.number();
        this.type = room.getRoomType();
        this.price = room.getPricePerNight();
    }

}

