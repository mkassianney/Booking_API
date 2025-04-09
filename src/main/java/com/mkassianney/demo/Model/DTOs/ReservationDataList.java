package com.mkassianney.demo.Model.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ReservationDataList (
        @NotNull
        Date checkIn,
        @NotNull
        Date checkOut
){}
