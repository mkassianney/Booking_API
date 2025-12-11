package com.mkassianney.demo.Model.DTORequest;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationData(
        @NotNull
        @JoinColumn(name = "check_in")
        LocalDate checkInDate,
        @NotNull
        @JoinColumn(name = "check_out")
        LocalDate checkOutDate,
        @NotNull
        @JoinColumn(name = "room_id")
        Long roomId,
        @NotNull
        @JoinColumn(name = "room_number")
        Integer roomNumber,
        @NotNull
        String clientCpf) {
}
