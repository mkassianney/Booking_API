package com.mkassianney.demo.DTOs;

import com.mkassianney.demo.Model.Enumerations.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RoomData(
        @NotNull
        int roomNumber,
        @NotNull
        @Enumerated(EnumType.STRING)
        RoomType roomType,
        @NotNull
        BigDecimal pricePerNight,
        @NotNull
        String description
){}
