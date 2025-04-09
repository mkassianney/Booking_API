package com.mkassianney.demo.Model.DTOs;

import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Model.Enumerations.roomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RoomDataList(
        @NotNull
        int roomNumber,
        @NotNull
        @Enumerated(EnumType.STRING)
        roomType roomType,
        @NotNull
        BigDecimal pricePerNight,
        @NotNull
        String description,
        @NotNull
        Boolean available
) {
    public RoomDataList(Room room) {
        this(room.getRoomNumber(), room.getRoomType(), room.getPricePerNight(), room.getDescription(), room.isAvailable());
    }
}
