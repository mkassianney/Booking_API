package com.mkassianney.demo.Model.Entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.Model.Enumerations.roomType;
import com.mkassianney.demo.Model.DTOs.ReservationData;
import com.mkassianney.demo.Model.DTOs.RoomData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkIn;
    private Date checkOut;
    private roomType type;
    private int number;
    private BigDecimal price;

    // para realizar a reserva precisa do número do quarto e em seguida fazer com que seu status mude;

    public Reservation(@Valid ReservationData reservationData, int roomNumber, roomType roomType, BigDecimal pricePerNight){}

    @JsonCreator
    public Reservation(@JsonProperty("value") @Valid ReservationData reservationData, RoomData roomData){
        this.checkIn = reservationData.checkIn();
        this.checkOut = reservationData.checkOut();
        this.number = roomData.roomNumber();
        this.type = roomData.roomType();
        this.price = roomData.pricePerNight();
    }
}
