package com.mkassianney.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.Model.Enumerations.roomType;
import com.mkassianney.demo.Model.RoomData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "room")
@Getter
@EqualsAndHashCode(of = "roomNumber")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int roomNumber;
    @Enumerated(EnumType.STRING)
    private roomType roomType;
    private BigDecimal pricePerNight;
    private String description;
    private boolean available = true;

    public Room(){}

    public Room(@JsonProperty("value") @Valid RoomData roomData){
        this.roomNumber = roomData.roomNumber();
        this.roomType = roomData.roomType();
        this.pricePerNight = roomData.pricePerNight();
        this.description = roomData.description();
    }
}
