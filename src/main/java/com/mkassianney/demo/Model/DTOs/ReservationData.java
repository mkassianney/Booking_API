package com.mkassianney.demo.Model.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record ReservationData(
        @NotNull
        @JoinColumn(name = "check_in")
        LocalDate checkIn,
        @NotNull
        @JoinColumn(name = "check_out")
        LocalDate checkOut,
        @NotNull
        @JoinColumn(name = "room_n")
        Integer number,
        Long client_id,
        String client_cpf) {
}
