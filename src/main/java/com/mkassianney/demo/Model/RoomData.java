package com.mkassianney.demo.Model;

import com.mkassianney.demo.Model.Enumerations.roomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

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
){}
