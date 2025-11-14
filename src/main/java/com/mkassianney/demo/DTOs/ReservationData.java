package com.mkassianney.demo.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public record ReservationData(
        @NotNull
        @JoinColumn(name = "check_in")
        LocalDate check_in,
        @NotNull
        Period duration,
        @NotNull
        @JoinColumn(name = "check_out")
        LocalDate check_out,
        @NotNull
        @JoinColumn(name = "room_n")
        Integer number,
        Long client_id,
        String client_cpf) {
}
