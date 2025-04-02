package com.mkassianney.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.Model.Enumerations.roomType;
import com.mkassianney.demo.Model.DTOs.RoomData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
    private boolean available;

    public Room(){}

    public Room(@JsonProperty("value") @Valid RoomData roomData){
        this.roomNumber = roomData.roomNumber();
        this.roomType = roomData.roomType();
        this.pricePerNight = roomData.pricePerNight();
        this.description = roomData.description();
        this.available = true;
    }

    public Long getId() {
        return Id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public com.mkassianney.demo.Model.Enumerations.roomType getRoomType() {
        return roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }
}
