package com.mkassianney.demo.Model.DTOs;

import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Model.Enumerations.roomType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public record RoomData(
        @NotNull
        int roomNumber,
        @NotNull
        @Enumerated(EnumType.STRING)
        roomType roomType,
        @NotNull
        BigDecimal pricePerNight,
        @NotNull
        String description
){
        public RoomData(Room room) {
                this(room.getRoomNumber(), room.getRoomType(), room.getPricePerNight(), room.getDescription());
        }
}
